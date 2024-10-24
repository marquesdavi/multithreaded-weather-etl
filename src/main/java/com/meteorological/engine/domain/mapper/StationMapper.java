package com.meteorological.engine.domain.mapper;

import com.meteorological.engine.domain.entity.StationEntity;
import com.meteorological.engine.domain.model.Station;

public class StationMapper {
    public static StationEntity mapToEntity(Station station){
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(station.getName());
        stationEntity.setCode(station.getCode());
        stationEntity.setUf(station.getUf());
        stationEntity.setLatitude(station.getLatitude());
        stationEntity.setLongitude(station.getLongitude());
        stationEntity.setAltitude(station.getAltitude());
        stationEntity.setRegion(station.getRegion());
        stationEntity.setFoundingDate(station.getFoundingDate());

        return stationEntity;
    }
}
