package com.meteorological.engine.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_climate_track")
public class ClimateTrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private StationEntity station;
    @Column(name = "date_time_utc")
    private LocalDateTime dateTime;
    @Column(name = "total_precipitation")
    private BigDecimal totalPrecipitation;
    @Column(name = "atm_pressure_station_level")
    private BigDecimal atmosphericPressureAtStationLevel;
    @Column(name = "max_atm_pressure_last_hour")
    private BigDecimal maxAtmosphericPressureLastHour;
    @Column(name = "min_atm_pressure_last_hour")
    private BigDecimal minAtmosphericPressureLastHour;
    @Column(name = "global_radiation")
    private BigDecimal globalRadiation;
    @Column(name = "dry_bulb_temperature")
    private BigDecimal dryBulbTemperature;
    @Column(name = "dew_point_temperature")
    private BigDecimal dewPointTemperature;
    @Column(name = "max_temperature_last_hour")
    private BigDecimal maxTemperatureLastHour;
    @Column(name = "min_temperature_last_hour")
    private BigDecimal minTemperatureLastHour;
    @Column(name = "max_dew_point_temperature_last_hour")
    private BigDecimal maxDewPointTemperatureLastHour;
    @Column(name = "min_dew_point_temperature_last_hour")
    private BigDecimal minDewPointTemperatureLastHour;
    @Column(name = "max_relative_humidity_last_hour")
    private Integer maxRelativeHumidityLastHour;
    @Column(name = "min_relative_humidity_last_hour")
    private Integer minRelativeHumidityLastHour;
    @Column(name = "hourly_relative_humidity")
    private Integer hourlyRelativeHumidity;
    @Column(name = "hourly_wind_direction")
    private Integer hourlyWindDirection;
    @Column(name = "max_wind_gust")
    private BigDecimal maxWindGust;
    @Column(name = "hourly_wind_speed")
    private BigDecimal hourlyWindSpeed;
}
