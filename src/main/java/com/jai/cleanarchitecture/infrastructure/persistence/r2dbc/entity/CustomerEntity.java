package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("customer")
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends PersonEntity{
    @Id
    @Column("customer_id")
    private Long customerId;

    @Column("password")
    private String password;

    @Column("status")
    private Boolean status;

    @Transient
    private List<AccountEntity> accounts;
}
