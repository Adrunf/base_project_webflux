package com.jai.cleanarchitecture.domain.usecase.customer;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
@Component
@RequiredArgsConstructor
public class FetchCustomer {
    private final CustomerRepository customerRepository;

    /**
     * Fetch a customer by id
     * @param id
     * @return
     */
    public Mono<Customer> fetchCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    /**
     * Fetch all customers
     * @return
     */
    public Flux<Customer> fetchAllCustomers() {
        return customerRepository.findAllCustomers();
    }
}
