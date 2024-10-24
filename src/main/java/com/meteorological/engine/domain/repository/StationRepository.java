package com.meteorological.engine.domain.repository;

import com.meteorological.engine.domain.entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, UUID> {
    boolean existsByCode(String code);
    StationEntity findByCode(String code);
}
