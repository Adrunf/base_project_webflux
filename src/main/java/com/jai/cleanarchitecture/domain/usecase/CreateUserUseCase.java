package com.jai.cleanarchitecture.domain.usecase;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        return Mono.justOrEmpty(user)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User payload must not be null")))
                .flatMap(this::validateUser)
                .flatMap(userRepository::save);
    }

    private Mono<User> validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            return Mono.error(new IllegalArgumentException("First name is required"));
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Last name is required"));
        }
        LocalDate birthDate = user.getBirthDate();
        if (birthDate == null) {
            return Mono.error(new IllegalArgumentException("Birth date is required"));
        }
        if (birthDate.isAfter(LocalDate.now())) {
            return Mono.error(new IllegalArgumentException("Birth date cannot be in the future"));
        }
        return Mono.just(user);
    }
}
