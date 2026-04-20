package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.CustomerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author arobayo
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    Customer toDomain(CustomerEntity customerEntity);

    CustomerEntity toEntity(Customer customer);

}
