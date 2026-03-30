package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.adapter;

import com.jai.cleanarchitecture.domain.model.User;

import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.adapter.UserRepositoryAdapter;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity.UserEntity;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.UserMapper;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.UserMapperImpl;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository.ReactiveUserRepository;
import com.jai.cleanarchitecture.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserRepositoryAdapterTest {
    ReactiveUserRepository reactiveMongoPersonRepository;
    UserMapper mongoUserMapper;
    UserRepositoryAdapter personRepositoryAdapter;
    DatabaseClient databaseClient;

    @BeforeEach
    void setUp() {
        mongoUserMapper = new UserMapperImpl();
        reactiveMongoPersonRepository = mock(ReactiveUserRepository.class);
        personRepositoryAdapter = new UserRepositoryAdapter(reactiveMongoPersonRepository, mongoUserMapper,databaseClient);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonModelShouldSaveAndReturnPersonModel(User user) {
        //GIVEN
        when(reactiveMongoPersonRepository.save(any(UserEntity.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<User> result = personRepositoryAdapter.save(user);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(user)
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndEntity")
    void givenIdentifierShouldReturnPersonModel(User user, UserEntity userEntity) {
        //GIVEN
        when(reactiveMongoPersonRepository.findById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(userEntity));
        //WHEN
        Mono<User> result = personRepositoryAdapter.findById(1L);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(user)
                .verifyComplete();
    }

}
