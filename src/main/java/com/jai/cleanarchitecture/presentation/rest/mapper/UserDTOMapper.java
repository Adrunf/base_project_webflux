package com.jai.cleanarchitecture.presentation.rest.mapper;

import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.presentation.rest.dto.UserDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDTOMapper {
    User toDomain(UserDTO userDTO);

    UserDTO toDTO(User user);
}