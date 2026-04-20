package com.jai.cleanarchitecture.presentation.rest.controller;

import com.jai.cleanarchitecture.domain.usecase.user.CreateUserUseCase;
import com.jai.cleanarchitecture.domain.usecase.user.FetchUserUseCase;
import com.jai.cleanarchitecture.presentation.rest.dto.UserDTO;
import com.jai.cleanarchitecture.presentation.rest.mapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final UserDTOMapper userDTOMapper;

    @PostMapping
    Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return createUserUseCase.createUser(userDTOMapper.toDomain(userDTO)).map(userDTOMapper::toDTO);
    }


    @GetMapping(path = "{id}")
    Mono<UserDTO> fetchUser(@PathVariable Long id) {
        return fetchUserUseCase.fetchById(id).map(userDTOMapper::toDTO);
    }

    @GetMapping(path = "/all")
    Flux<UserDTO> getAllUsers() {
        return fetchUserUseCase.fetchAll().map(userDTOMapper::toDTO);
    }
}
