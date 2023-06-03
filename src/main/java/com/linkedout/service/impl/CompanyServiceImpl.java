package com.linkedout.service.impl;

import com.linkedout.model.entity.Company;
import com.linkedout.model.service.CompanyServiceModel;
import com.linkedout.repository.CompanyRepository;
import com.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel) {
        Company company = this.modelMapper.map(companyServiceModel, Company.class);
        return modelMapper.map(this.companyRepository.saveAndFlush(company), CompanyServiceModel.class);
    }
}