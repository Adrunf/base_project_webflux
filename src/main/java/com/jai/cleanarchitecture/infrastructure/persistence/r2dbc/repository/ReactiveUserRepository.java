package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository;

import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveUserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
