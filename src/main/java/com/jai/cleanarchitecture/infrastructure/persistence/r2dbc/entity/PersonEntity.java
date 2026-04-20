package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Column("name")
    private String name;

    @Column("gender")
    private String gender;

    @Column("age")
    private int age;

    @Column("identification")
    private String identification;

    @Column("address")
    private String address;

    @Column("phone")
    private String phone;
}
