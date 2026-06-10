package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.adapter;

import com.jai.cleanarchitecture.domain.model.Customer;
import com.jai.cleanarchitecture.domain.repository.CustomerRepository;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.CustomerMapper;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository.ReactiveCustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author arobayo
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final ReactiveCustomerRepository reactiveCustomerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        return reactiveCustomerRepository.save(customerMapper.toEntity(customer))
                .map(customerMapper::toDomain);
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        return reactiveCustomerRepository.save(customerMapper.toEntity(customer))
                .map(customerMapper::toDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return reactiveCustomerRepository.deleteById(id);
    }

    @Override
    public Mono<Customer> findCustomerById(Long id) {
        return reactiveCustomerRepository.findById(id).map(customerMapper::toDomain);
    }

    @Override
    public Flux<Customer> findAllCustomers() {
        return reactiveCustomerRepository.findAll().map(customerMapper::toDomain);
    }
}
