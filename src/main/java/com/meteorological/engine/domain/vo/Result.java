package com.meteorological.engine.domain.vo;

import com.meteorological.engine.domain.model.ClimateTrack;
import com.meteorological.engine.domain.model.Station;

import java.util.List;

public record Result(
        Station station,
        List<ClimateTrack> tracks
) {

}
