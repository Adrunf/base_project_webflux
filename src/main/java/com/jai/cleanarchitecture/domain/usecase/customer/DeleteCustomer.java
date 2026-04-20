package com.jai.cleanarchitecture.domain.usecase.customer;

import com.jai.cleanarchitecture.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
@Component
@RequiredArgsConstructor
public class DeleteCustomer {
    private final CustomerRepository customerRepository;

    /**
     * Delete a customer by id
     * @param id
     * @return
     */
    public Mono<Void> delete(Long id) {
        return customerRepository.delete(id);
    }
}
