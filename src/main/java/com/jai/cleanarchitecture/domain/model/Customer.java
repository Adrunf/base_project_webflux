package com.jai.cleanarchitecture.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person{
    private Long customerId;
    private String password;
    private Boolean status;
    private List<Account> accounts;
}
