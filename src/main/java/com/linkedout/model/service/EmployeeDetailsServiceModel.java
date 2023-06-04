package com.linkedout.model.service;

import com.linkedout.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsServiceModel {
    private String uuid;
    private String firstName;
    private String lastName;
    private String educationLevel;
    private String jobTitle;
    private LocalDate birthDate;
    private BigDecimal salary;
    private Company company;
}