package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMapper {
    Person toDomain(PersonEntity personEntity);
    PersonEntity toEntity(Person person);
}
