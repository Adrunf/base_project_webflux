package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Table("transaction")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @Column("transaction_id")
    private Long transactionId;

    @Column("transaction_type")
    private String transactionType;

    @Column("amount")
    private Double amount;

    @Column("balance")
    private Double balance;

    @Column("date")
    private LocalDate date;

    @Transient
    private AccountEntity account;

    @Column("status")
    private Boolean status;
}
