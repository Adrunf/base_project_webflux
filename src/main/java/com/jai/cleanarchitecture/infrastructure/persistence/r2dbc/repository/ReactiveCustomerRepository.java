package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository;

import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author arobayo
 */
public interface ReactiveCustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {
}
