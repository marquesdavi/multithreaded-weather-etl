package com.meteorological.engine.domain.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Station {
    private String name;
    private String code;
    private LocalDate foundingDate;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;
    private String region;
    private String uf;
}

