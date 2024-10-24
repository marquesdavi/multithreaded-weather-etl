package com.meteorological.engine.core.processor;

import com.meteorological.engine.core.handler.CsvHandler;
import com.meteorological.engine.domain.entity.ClimateTrackEntity;
import com.meteorological.engine.domain.entity.StationEntity;
import com.meteorological.engine.domain.mapper.ClimateTrackMapper;
import com.meteorological.engine.domain.mapper.StationMapper;
import com.meteorological.engine.domain.model.Station;
import com.meteorological.engine.domain.repository.ClimateTrackRepository;
import com.meteorological.engine.domain.repository.StationRepository;
import com.meteorological.engine.domain.vo.Result;
import com.meteorological.engine.service.DataProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataProcessor implements DataProcessorService {
    private final CsvHandler csvHandler;
    private final ClimateTrackRepository trackRepository;
    private final StationRepository stationRepository;

    @Override
    public Result processData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Station station = csvHandler.extractStationData(br);
            csvHandler.processClimateData(br, station);

            return csvHandler.getResult();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo CSV", e);
        }
    }

    public void loadToDatabase(String filePath) {
        Result result = processData(filePath);

        saveStationIfNotExist(result.station());

        StationEntity station = findByCode(result.station().getCode());

        List<ClimateTrackEntity> trackList = result.tracks().stream()
                .map((track) -> ClimateTrackMapper.mapToEntity(track, station))
                .toList();

        trackRepository.saveAll(trackList);
    }

    public StationEntity findByCode(String code) {
        return stationRepository.findByCode(code);
    }

    public void saveStationIfNotExist(Station station){
        if(!stationRepository.existsByCode(station.getCode())){
            StationEntity stationEntity = StationMapper.mapToEntity(station);
            stationRepository.save(stationEntity);
        }
    }
}
