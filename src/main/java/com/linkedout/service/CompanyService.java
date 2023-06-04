package com.linkedout.service;

import com.linkedout.model.entity.Company;
import com.linkedout.model.service.CompanyServiceModel;

import java.util.List;

public interface CompanyService {
    CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel);

    List<String> getAllCompanyNames();

    Company getCompanyByName(String name);

    List<CompanyServiceModel> getAllCompanies();

    void deleteCompany(String id);
}