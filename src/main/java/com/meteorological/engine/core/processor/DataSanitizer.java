package com.meteorological.engine.core.processor;

import com.meteorological.engine.domain.model.ClimateTrack;
import com.meteorological.engine.domain.model.Station;
import com.meteorological.engine.service.DataSanitizerService;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class DataSanitizer implements DataSanitizerService {

    @Override
    public Station sanitizeStation(BufferedReader br) throws IOException {
        Station station = new Station();

        Map<String, Consumer<String>> fieldMapping = createStationFieldMapping(station);

        String line;
        while ((line = br.readLine()) != null && !line.startsWith("Data;")) {
            String[] parts = line.split(";");
            if (parts.length == 2) {
                String key = parts[0].replace(":", "").trim();
                String value = parts[1].trim();
                Consumer<String> consumer = fieldMapping.get(key);
                if (consumer != null) {
                    consumer.accept(value);
                }
            }
        }
        return station;
    }

    @Override
    public ClimateTrack sanitizeClimateTrack(CSVRecord csvRecord) {
        ClimateTrack climateTrack = new ClimateTrack();

        climateTrack.setDateTime(parseAndValidateDateTime(csvRecord.get(0), csvRecord.get(1)));
        climateTrack.setTotalPrecipitation(parseAndValidateBigDecimal(csvRecord.get(2)));
        climateTrack.setAtmosphericPressureAtStationLevel(parseAndValidateBigDecimal(csvRecord.get(3)));
        climateTrack.setMaxAtmosphericPressureLastHour(parseAndValidateBigDecimal(csvRecord.get(4)));
        climateTrack.setMinAtmosphericPressureLastHour(parseAndValidateBigDecimal(csvRecord.get(5)));
        climateTrack.setGlobalRadiation(parseAndValidateBigDecimal(csvRecord.get(6)));
        climateTrack.setDryBulbTemperature(parseAndValidateBigDecimal(csvRecord.get(7)));
        climateTrack.setDewPointTemperature(parseAndValidateBigDecimal(csvRecord.get(8)));
        climateTrack.setMaxTemperatureLastHour(parseAndValidateBigDecimal(csvRecord.get(9)));
        climateTrack.setMinTemperatureLastHour(parseAndValidateBigDecimal(csvRecord.get(10)));
        climateTrack.setMaxDewPointTemperatureLastHour(parseAndValidateBigDecimal(csvRecord.get(11)));
        climateTrack.setMinDewPointTemperatureLastHour(parseAndValidateBigDecimal(csvRecord.get(12)));
        climateTrack.setMaxRelativeHumidityLastHour(parseAndValidateInteger(csvRecord.get(13)));
        climateTrack.setMinRelativeHumidityLastHour(parseAndValidateInteger(csvRecord.get(14)));
        climateTrack.setHourlyRelativeHumidity(parseAndValidateInteger(csvRecord.get(15)));
        climateTrack.setHourlyWindDirection(parseAndValidateInteger(csvRecord.get(16)));
        climateTrack.setMaxWindGust(parseAndValidateBigDecimal(csvRecord.get(17)));
        climateTrack.setHourlyWindSpeed(parseAndValidateBigDecimal(csvRecord.get(18)));

        return climateTrack;
    }

    private Map<String, Consumer<String>> createStationFieldMapping(Station station) {
        Map<String, Consumer<String>> functionToField = new HashMap<>();
        functionToField.put("REGIAO", station::setRegion);
        functionToField.put("UF", station::setUf);
        functionToField.put("ESTACAO", station::setName);
        functionToField.put("CODIGO (WMO)", station::setCode);
        functionToField.put("LATITUDE", value -> station.setLatitude(parseAndValidateBigDecimal(value)));
        functionToField.put("LONGITUDE", value -> station.setLongitude(parseAndValidateBigDecimal(value)));
        functionToField.put("ALTITUDE", value -> station.setAltitude(parseAndValidateBigDecimal(value))); // Mudança aqui
        functionToField.put("DATA DE FUNDACAO", value -> station.setFoundingDate(parseAndValidateLocalDate(value)));
        return functionToField;
    }

    private LocalDateTime parseAndValidateDateTime(String date, String utcTime) {
        return date != null && !date.isEmpty() && utcTime != null && !utcTime.isEmpty()
                ? formatToUTC(date, utcTime)
                : null;
    }

    private BigDecimal parseAndValidateBigDecimal(String value) {
        return value != null && !value.isEmpty() ? new BigDecimal(value.replace(",", ".")) : null;
    }

    private Integer parseAndValidateInteger(String value) {
        return value != null && !value.isEmpty() ? Integer.parseInt(value) : null;
    }

    private LocalDate parseAndValidateLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return value != null && !value.isEmpty() ? LocalDate.parse(value, formatter) : null;
    }

    public static LocalDateTime formatToUTC(String date, String utcTime) {
        String time = utcTime.replace(" UTC", "");

        if (time.length() == 4) {
            time = time.substring(0, 2) + ":" + time.substring(2, 4);
        }

        String dateTimeString = date + " " + time;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
