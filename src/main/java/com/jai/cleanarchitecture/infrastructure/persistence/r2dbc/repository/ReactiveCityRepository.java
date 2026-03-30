package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository;

import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.CityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveCityRepository extends ReactiveCrudRepository<CityEntity, Long> {
}
