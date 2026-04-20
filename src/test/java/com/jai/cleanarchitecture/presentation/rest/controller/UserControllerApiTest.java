package com.jai.cleanarchitecture.presentation.rest.controller;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.usecase.user.CreateUserUseCase;
import com.jai.cleanarchitecture.domain.usecase.user.FetchUserUseCase;
import com.jai.cleanarchitecture.presentation.rest.dto.UserDTO;
import com.jai.cleanarchitecture.presentation.rest.mapper.UserDTOMapper;
import com.jai.cleanarchitecture.presentation.rest.mapper.UserDTOMapperImpl;
import com.jai.cleanarchitecture.providers.PersonDTOProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserControllerApiTest {

    CreateUserUseCase createUserUseCase;
    FetchUserUseCase fetchUserUseCase;
    UserDTOMapper userDTOMapper;
    UserController userControllerApi;

    @BeforeEach
    void setUp() {
        userDTOMapper = new UserDTOMapperImpl();
        createUserUseCase = mock(CreateUserUseCase.class);
        fetchUserUseCase = mock(FetchUserUseCase.class);
        userControllerApi = new UserController(createUserUseCase, fetchUserUseCase, userDTOMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    void givenPersonDTOShouldCreateUser(UserDTO userDTO) {
        //GIVE
        when(createUserUseCase.createUser(any(User.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<UserDTO> result = userControllerApi.createUser(userDTO);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(userDTO)
                .verifyComplete();
        verify(createUserUseCase, times(1)).createUser(any(User.class));
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void whenFindByIdShouldReturnPersonDTO(User user, UserDTO userDTO) {
        //GIVEN
        when(fetchUserUseCase.fetchById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(user));
        //WHEN
        Mono<UserDTO> result = userControllerApi.fetchUser(1L);
        //THEN
        StepVerifier.create(result)
                .expectNext(userDTO)
                .verifyComplete();
    }

}
