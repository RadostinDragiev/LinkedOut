package com.linkedout.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String town;
    private BigDecimal budget;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}
