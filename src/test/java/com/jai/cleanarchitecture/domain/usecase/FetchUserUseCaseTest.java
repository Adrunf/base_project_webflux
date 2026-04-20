package com.jai.cleanarchitecture.domain.usecase;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.repository.UserRepository;
import com.jai.cleanarchitecture.domain.usecase.user.FetchUserUseCase;
import com.jai.cleanarchitecture.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FetchUserUseCaseTest {

    UserRepository userRepository;
    FetchUserUseCase fetchUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        fetchUserUseCase = new FetchUserUseCase(userRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenFetchPersonByIdShouldReturnPerson(User user) {
        //GIVEN
        when(userRepository.findById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(user));
        //WHEN
        Mono<User> result = fetchUserUseCase.fetchById(anyLong());
        //THEN
        StepVerifier
                .create(result)
                .expectNext(user)
                .verifyComplete();
        verify(userRepository, times(1)).findById(anyLong());
    }
}
