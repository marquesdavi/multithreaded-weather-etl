package com.meteorological.engine.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_station")
public class StationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "founding_date")
    private LocalDate foundingDate;
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "altitude")
    private BigDecimal altitude;
    @Column(name = "region")
    private String region;
    @Column(name = "uf")
    private String uf;
    @OneToMany(mappedBy = "station")
    private Set<ClimateTrackEntity> climateTracks = new HashSet<ClimateTrackEntity>();
}
