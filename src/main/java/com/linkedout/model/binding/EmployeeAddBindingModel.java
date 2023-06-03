package com.linkedout.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddBindingModel {
    @NotNull
    @Length(min = 2, message = "First name length must be between 2 and 10")
    private String firstName;
    @NotNull
    @Length(min = 2, message = "Last name length must be between 2 and 10")
    private String lastName;
    @NotNull(message = "Education level should not be empty")
    private String educationLevel;
    @NotNull(message = "Job title should not be empty")
    private String jobTitle;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @NotNull(message = "Salary should not be empty")
    @Positive(message = "Salary should be positive number")
    private BigDecimal salary;
    @NotNull(message = "Please choose company")
    private String company;
}
