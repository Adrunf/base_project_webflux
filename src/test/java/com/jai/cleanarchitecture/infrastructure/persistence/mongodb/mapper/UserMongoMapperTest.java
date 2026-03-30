package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.mapper;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.UserEntity;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.UserMapper;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.UserMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class UserMongoMapperTest {

    UserMapper personMongoMapper;

    @BeforeEach
    void setUp() {
        personMongoMapper = new UserMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndEntity")
    void givenPersonEntityShouldMapPersonModel(User user, UserEntity userEntity) {
        Assertions.assertEquals(personMongoMapper.toEntity(user), userEntity);
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndEntity")
    void givenPersonModelShouldMapPersonEntity(User user, UserEntity userEntity) {
        Assertions.assertEquals(personMongoMapper.toDomain(userEntity), user);
    }

}
