package com.jai.cleanarchitecture.domain.usecase.customer;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
@Component
@RequiredArgsConstructor
public class CreateCustomer {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Save a customer
     * @param customer
     * @return
     */
    public Mono<Customer> createCustomer(Customer customer) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);

        return customerRepository.createCustomer(customer);
    }


}
