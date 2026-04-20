package com.jai.cleanarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long transactionId;
    private String transactionType;
    private Double amount;
    private Double balance;
    private LocalDate date;
    private Account account;
    private Boolean status;
}
