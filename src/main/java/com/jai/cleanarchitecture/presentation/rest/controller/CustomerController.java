package com.jai.cleanarchitecture.presentation.rest.controller;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.usecase.customer.CreateCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.DeleteCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.FetchCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.UpdateCustomer;
import com.jai.cleanarchitecture.presentation.rest.dto.CustomerDTO;
import com.jai.cleanarchitecture.presentation.rest.mapper.CustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer controller
 * @author arobayo
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CreateCustomer createCustomer;
    private final FetchCustomer fetchCustomer;
    private final UpdateCustomer updateCustomer;
    private final DeleteCustomer deleteCustomer;
    private final CustomerDTOMapper customerDTOMapper;


    @PostMapping
    Mono<CustomerDTO> createCustomer(@RequestBody Customer customer) {
        return createCustomer.createCustomer(customer)
                .map(customerDTOMapper::toDTO);
    }

    @GetMapping(path = "{id}")
    Mono<CustomerDTO> fetchCustomer(@PathVariable Long id) {
        return fetchCustomer.fetchCustomerById(id)
                .map(customerDTOMapper::toDTO);
    }

    @GetMapping(path = "/all")
    Flux<CustomerDTO> getAllCustomers() {
        return fetchCustomer.fetchAllCustomers()
                .map(customerDTOMapper::toDTO);
    }

    @DeleteMapping(path = "/{id}")
    Mono<Void> deleteCustomer(@PathVariable Long id) {
        return deleteCustomer.delete(id);
    }

    @PutMapping
    Mono<CustomerDTO> updateCustomer(@RequestBody Customer customer) {
        return updateCustomer.updateCustomer(customer)
                .map(customerDTOMapper::toDTO);
    }
}
