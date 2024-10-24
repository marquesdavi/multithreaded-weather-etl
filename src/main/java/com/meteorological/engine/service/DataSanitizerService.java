package com.meteorological.engine.service;

import com.meteorological.engine.domain.model.ClimateTrack;
import com.meteorological.engine.domain.model.Station;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;

public interface DataSanitizerService {
    Station sanitizeStation(BufferedReader br) throws IOException;
    ClimateTrack sanitizeClimateTrack(CSVRecord csvRecord);
}
