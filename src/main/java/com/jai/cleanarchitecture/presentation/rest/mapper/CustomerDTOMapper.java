package com.jai.cleanarchitecture.presentation.rest.mapper;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.presentation.rest.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDTOMapper {
    CustomerDTO toDTO(Customer customer);
}
