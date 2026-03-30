package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);
}