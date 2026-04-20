package com.jai.cleanarchitecture.presentation.rest.controller;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.usecase.customer.CreateCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.DeleteCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.FetchCustomer;
import com.jai.cleanarchitecture.domain.usecase.customer.UpdateCustomer;
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


    @PostMapping
    Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return createCustomer.createCustomer(customer);
    }

    @GetMapping(path = "{id}")
    Mono<Customer> fetchCustomer(@PathVariable Long id) {
        return fetchCustomer.fetchCustomerById(id);
    }

    @GetMapping(path = "/all")
    Flux<Customer> getAllCustomers() {
        return fetchCustomer.fetchAllCustomers();
    }

    @DeleteMapping(path = "/{id}")
    Mono<Void> deleteCustomer(@PathVariable Long id) {
        return deleteCustomer.delete(id);
    }

    @PutMapping
    Mono<Customer> updateCustomer(@RequestBody Customer customer) {
        return updateCustomer.updateCustomer(customer);
    }
}
