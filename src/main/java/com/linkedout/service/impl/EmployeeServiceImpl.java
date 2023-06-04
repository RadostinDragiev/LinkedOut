package com.linkedout.service.impl;

import com.linkedout.model.entity.Employee;
import com.linkedout.model.service.EmployeeDetailsServiceModel;
import com.linkedout.model.service.EmployeeServiceModel;
import com.linkedout.repository.EmployeeRepository;
import com.linkedout.service.CompanyService;
import com.linkedout.service.EmployeeService;
import com.linkedout.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public EmployeeServiceModel addEmployee(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);
        employee.setCompany(this.companyService.getCompanyByName(employeeServiceModel.getCompany()));
        if (this.validationUtil.isValid(employee)) {
            return this.modelMapper.map(this.employeeRepository.saveAndFlush(employee), EmployeeServiceModel.class);
        } else {
            this.validationUtil.getViolations(employee)
                    .forEach(violation -> log.error(violation.getMessage()));
            return employeeServiceModel;
        }
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        return Arrays.asList(this.modelMapper.map(this.employeeRepository.findAll(), EmployeeServiceModel[].class));
    }

    @Override
    public EmployeeDetailsServiceModel getEmployeeById(String id) {
        return this.modelMapper.map(this.employeeRepository.findById(id), EmployeeDetailsServiceModel.class);
    }

    @Override
    public void deleteEmployeeById(String id) {
        this.employeeRepository.deleteById(id);
    }
}