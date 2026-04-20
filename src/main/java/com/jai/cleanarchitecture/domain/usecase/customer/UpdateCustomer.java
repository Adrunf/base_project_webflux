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
public class UpdateCustomer {
    private final CustomerRepository customerRepository;

    /**
     * Update a customer
     * @param customer
     * @return
     */
    public Mono<Customer> updateCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            return Mono.error(new IllegalArgumentException("Customer ID is required"));
        }

        return customerRepository.findCustomerById(customer.getCustomerId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found")))
                .flatMap(found -> customerRepository.update(customer));
    }
}
