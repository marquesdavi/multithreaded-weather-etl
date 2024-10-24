package com.meteorological.engine.domain.mapper;

import com.meteorological.engine.domain.entity.ClimateTrackEntity;
import com.meteorological.engine.domain.entity.StationEntity;
import com.meteorological.engine.domain.model.ClimateTrack;

public class ClimateTrackMapper {
    public static ClimateTrackEntity mapToEntity(ClimateTrack climateTrack, StationEntity stationEntity){
        ClimateTrackEntity climateTrackEntity = new ClimateTrackEntity();

        climateTrackEntity.setStation(stationEntity);
        climateTrackEntity.setDateTime(climateTrack.getDateTime());
        climateTrackEntity.setTotalPrecipitation(climateTrack.getTotalPrecipitation());
        climateTrackEntity.setAtmosphericPressureAtStationLevel(climateTrack.getAtmosphericPressureAtStationLevel());
        climateTrackEntity.setMaxAtmosphericPressureLastHour(climateTrack.getMaxAtmosphericPressureLastHour());
        climateTrackEntity.setMinAtmosphericPressureLastHour(climateTrack.getMinAtmosphericPressureLastHour());
        climateTrackEntity.setGlobalRadiation(climateTrack.getGlobalRadiation());
        climateTrackEntity.setDryBulbTemperature(climateTrack.getDryBulbTemperature());
        climateTrackEntity.setDewPointTemperature(climateTrack.getDewPointTemperature());
        climateTrackEntity.setMaxTemperatureLastHour(climateTrack.getMaxTemperatureLastHour());
        climateTrackEntity.setMinTemperatureLastHour(climateTrack.getMinTemperatureLastHour());
        climateTrackEntity.setMaxDewPointTemperatureLastHour(climateTrack.getMaxDewPointTemperatureLastHour());
        climateTrackEntity.setMinDewPointTemperatureLastHour(climateTrack.getMinDewPointTemperatureLastHour());
        climateTrackEntity.setMaxRelativeHumidityLastHour(climateTrack.getMaxRelativeHumidityLastHour());
        climateTrackEntity.setMinRelativeHumidityLastHour(climateTrack.getMinRelativeHumidityLastHour());
        climateTrackEntity.setHourlyRelativeHumidity(climateTrack.getHourlyRelativeHumidity());
        climateTrackEntity.setHourlyWindDirection(climateTrack.getHourlyWindDirection());
        climateTrackEntity.setMaxWindGust(climateTrack.getMaxWindGust());
        climateTrackEntity.setHourlyWindSpeed(climateTrack.getHourlyWindSpeed());

        return climateTrackEntity;
    }
}
