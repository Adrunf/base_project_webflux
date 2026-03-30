package com.jai.cleanarchitecture.providers;

import com.jai.cleanarchitecture.domain.model.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

public class PersonProvider implements ArgumentsProvider {

    User user = User.builder()
            .id(1L)
            .firstName("John")
            .lastName("Smith")
            .birthDate(LocalDate.of(2000, 10, 20))
            .build();

    public User getPerson() {
        return user;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of(user));
    }
}