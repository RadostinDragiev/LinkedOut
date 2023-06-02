package com.linkedout.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    private BigDecimal salary;
    @Column(name = "education_level")
    private String educationLevel;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
}
