package com.jai.cleanarchitecture.domain.usecase.customer;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
@Component
@RequiredArgsConstructor
public class CreateCustomer {
    private final CustomerRepository customerRepository;

    /**
     * Save a customer
     * @param customer
     * @return
     */
    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }


}
