package com.jai.cleanarchitecture.providers;

import com.jai.cleanarchitecture.presentation.rest.dto.CityDTO;
import com.jai.cleanarchitecture.presentation.rest.dto.UserDTO;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

public class PersonDTOProvider implements ArgumentsProvider {
    CityDTO cityDTO = new CityDTO(1L, "New York");
    UserDTO userDTO = new UserDTO(1L, "John", "Smith", LocalDate.of(2000, 10, 20), cityDTO);

    public UserDTO getPersonDTO() {
        return userDTO;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of(userDTO));
    }
}