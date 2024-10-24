package com.meteorological.engine.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ClimateTrack {
    private LocalDateTime dateTime;
    private BigDecimal totalPrecipitation;
    private BigDecimal atmosphericPressureAtStationLevel;
    private BigDecimal maxAtmosphericPressureLastHour;
    private BigDecimal minAtmosphericPressureLastHour;
    private BigDecimal globalRadiation;
    private BigDecimal dryBulbTemperature;
    private BigDecimal dewPointTemperature;
    private BigDecimal maxTemperatureLastHour;
    private BigDecimal minTemperatureLastHour;
    private BigDecimal maxDewPointTemperatureLastHour;
    private BigDecimal minDewPointTemperatureLastHour;
    private Integer maxRelativeHumidityLastHour;
    private Integer minRelativeHumidityLastHour;
    private Integer hourlyRelativeHumidity;
    private Integer hourlyWindDirection;
    private BigDecimal maxWindGust;
    private BigDecimal hourlyWindSpeed;
}
