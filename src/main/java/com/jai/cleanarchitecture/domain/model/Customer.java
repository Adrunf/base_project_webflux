package com.jai.cleanarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long customerId;
    private String password;
    private Boolean status;
    private Person person;
    private List<Account> accounts;
}
