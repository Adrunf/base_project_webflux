package com.jai.cleanarchitecture.domain.usecase;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.repository.UserRepository;
import com.jai.cleanarchitecture.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    UserRepository userRepository;
    CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        createUserUseCase = new CreateUserUseCase(userRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonToSaveShouldReturnPersonModel(User user) {
        //GIVEN
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> Mono.just(user));
        //WHEN
        Mono<User> result = createUserUseCase.createUser(user);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(user)
                .verifyComplete();
        verify(userRepository, times(1)).save(user);
    }


}
