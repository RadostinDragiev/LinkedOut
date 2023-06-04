package com.linkedout.service;

import com.linkedout.model.service.EmployeeDetailsServiceModel;
import com.linkedout.model.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    EmployeeServiceModel addEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> getAllEmployees();

    EmployeeDetailsServiceModel getEmployeeById(String id);

    void deleteEmployeeById(String id);
}