package com.jai.cleanarchitecture.presentation.rest.mapper;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.presentation.rest.dto.UserDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class UserDTOMapperTest {

    UserDTOMapper userDTOMapper;

    @BeforeEach
    void setUp() {
        userDTOMapper = new UserDTOMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void givenPersonDTOShouldMapPersonModel(User user, UserDTO userDTO) {
        Assertions.assertEquals(userDTOMapper.toDomain(userDTO), user);
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void givenPersonModelShouldMapPersonDTO(User user, UserDTO userDTO) {
        Assertions.assertEquals(userDTOMapper.toDTO(user), userDTO);
    }
}
