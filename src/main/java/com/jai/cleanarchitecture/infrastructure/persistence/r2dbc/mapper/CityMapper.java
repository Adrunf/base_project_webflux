package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper;

import com.jai.cleanarchitecture.domain.model.City;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toDomain(CityEntity cityEntity);

    CityEntity toEntity(City city);
}
