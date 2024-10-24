package com.meteorological.engine.core.handler;

import com.meteorological.engine.domain.model.ClimateTrack;
import com.meteorological.engine.domain.model.Station;
import com.meteorological.engine.domain.vo.Result;
import com.meteorological.engine.service.DataSanitizerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class CsvHandler {
    private final DataSanitizerService dataSanitizerService;
    private Result result;

    private static final String[] HEADERS = {
            "Data",
            "Hora UTC",
            "PRECIPITA��O TOTAL, HOR�RIO (mm)",
            "PRESSAO ATMOSFERICA AO NIVEL DA ESTACAO, HORARIA (mB)",
            "PRESS�O ATMOSFERICA MAX.NA HORA ANT. (AUT) (mB)",
            "PRESS�O ATMOSFERICA MIN. NA HORA ANT. (AUT) (mB)",
            "RADIACAO GLOBAL (Kj/m�)",
            "TEMPERATURA DO AR - BULBO SECO, HORARIA (�C)",
            "TEMPERATURA DO PONTO DE ORVALHO (�C)",
            "TEMPERATURA M�XIMA NA HORA ANT. (AUT) (�C)",
            "TEMPERATURA M�NIMA NA HORA ANT. (AUT) (�C)",
            "TEMPERATURA ORVALHO MAX. NA HORA ANT. (AUT) (�C)",
            "TEMPERATURA ORVALHO MIN. NA HORA ANT. (AUT) (�C)",
            "UMIDADE REL. MAX. NA HORA ANT. (AUT) (%)",
            "UMIDADE REL. MIN. NA HORA ANT. (AUT) (%)",
            "UMIDADE RELATIVA DO AR, HORARIA (%)",
            "VENTO, DIRE��O HORARIA (gr) (� (gr))",
            "VENTO, RAJADA MAXIMA (m/s)",
            "VENTO, VELOCIDADE HORARIA (m/s)"
    };

    public Station extractStationData(BufferedReader br) throws IOException {
        return dataSanitizerService.sanitizeStation(br);
    }

    public void processClimateData(BufferedReader br, Station station) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .setDelimiter(';')
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(br);

        List<ClimateTrack> climateTracks = StreamSupport.stream(records.spliterator(), false)
                .map(dataSanitizerService::sanitizeClimateTrack)
                .toList();

        this.result = new Result(station, climateTracks);
    }

    public Result getResult() {
        return result;
    }
}
