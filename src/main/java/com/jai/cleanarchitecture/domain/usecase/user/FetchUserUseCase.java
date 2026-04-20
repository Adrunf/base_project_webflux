package com.jai.cleanarchitecture.domain.usecase.user;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FetchUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> fetchById(Long id) {
        return userRepository.findById(id);
    }

    public Flux<User> fetchAll() {
        return userRepository.findAll();
    }

}
