package com.jai.cleanarchitecture.domain.repository;

import com.jai.cleanarchitecture.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
public interface CustomerRepository {
    Mono<Customer> createCustomer(Customer customer);

    Mono<Customer> update(Customer customer);

    Mono<Void> delete(Long id);

    Mono<Customer> findCustomerById(Long id);

    Flux<Customer> findAllCustomers();
}
