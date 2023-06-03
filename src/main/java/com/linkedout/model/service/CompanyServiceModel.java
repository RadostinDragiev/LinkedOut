package com.linkedout.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyServiceModel {
    @NotNull
    @Length(min = 2, max = 10, message = "Name length must be between 2 and 10")
    private String name;
    @NotNull
    @Length(min = 2, max = 10, message = "Town length must be between 2 and 10")
    private String town;
    @NotNull
    @Length(min = 10, message = "Description length must be more than 10")
    private String description;
    @NotNull(message = "Budget must not be empty")
    @Positive(message = "Budget must be positive number")
    private BigDecimal budget;
}