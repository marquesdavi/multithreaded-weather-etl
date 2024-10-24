package com.meteorological.engine.service;

import com.meteorological.engine.domain.vo.Result;

public interface DataProcessorService {
    Result processData(String filePath);
}