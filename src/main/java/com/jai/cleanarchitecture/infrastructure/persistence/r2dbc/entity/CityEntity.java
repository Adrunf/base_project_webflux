package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author arobayo
 */
@Table("city")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    @Id
    private Long id;

    @Column("name")
    private String name;
}
