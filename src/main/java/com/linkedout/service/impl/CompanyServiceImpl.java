package com.linkedout.service.impl;

import com.linkedout.model.entity.Company;
import com.linkedout.model.service.CompanyServiceModel;
import com.linkedout.repository.CompanyRepository;
import com.linkedout.service.CompanyService;
import com.linkedout.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel) {
        Company company = this.modelMapper.map(companyServiceModel, Company.class);
        if (this.validationUtil.isValid(company)) {
            return modelMapper.map(this.companyRepository.saveAndFlush(company), CompanyServiceModel.class);
        } else {
            this.validationUtil.getViolations(company).forEach(violation -> log.error(violation.getMessage()));
            return companyServiceModel;
        }
    }

    @Override
    public List<String> getAllCompanyNames() {
        return this.companyRepository.findAll().stream().map(Company::getName).collect(Collectors.toList());
    }

    @Override
    public Company getCompanyByName(String name) {
        return this.companyRepository.findByName(name);
    }

    @Override
    public List<CompanyServiceModel> getAllCompanies() {
        return Arrays.stream(this.modelMapper.map(this.companyRepository.findAll(), CompanyServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCompany(String id) {
        this.companyRepository.deleteById(id);
    }
}