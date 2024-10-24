package com.meteorological.engine.domain.repository;

import com.meteorological.engine.domain.entity.ClimateTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClimateTrackRepository extends JpaRepository<ClimateTrackEntity, UUID> {
}
