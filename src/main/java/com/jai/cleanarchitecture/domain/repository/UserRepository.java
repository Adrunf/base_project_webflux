package com.jai.cleanarchitecture.domain.repository;

import com.jai.cleanarchitecture.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> save(User user);

    Mono<User> findById(Long id);

    Flux<User> findAll();
}
