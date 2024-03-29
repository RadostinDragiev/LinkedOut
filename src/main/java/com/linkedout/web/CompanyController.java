package com.linkedout.web;

import com.linkedout.model.binding.CompanyAddBindingModel;
import com.linkedout.model.service.CompanyServiceModel;
import com.linkedout.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAddCompany(Model model) {
        if (!model.containsAttribute("companyAddBindingModel")) {
            model.addAttribute("companyAddBindingModel", new CompanyAddBindingModel());
        }
        return "company-add";
    }

    @PostMapping("/add")
    public String addCompany(@Valid @ModelAttribute("companyAddBindingModel") CompanyAddBindingModel companyAddBindingModel, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyAddBindingModel", bindingResult);
            return "redirect:/companies/add";
        }

        this.companyService.addCompany(this.modelMapper.map(companyAddBindingModel, CompanyServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("all")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies",
                Arrays.stream(this.modelMapper.map(this.companyService.getAllCompanies(), CompanyServiceModel[].class)).collect(Collectors.toList()));
        return "company-all";
    }

    @GetMapping("/details/{name}")
    public String getDetails(@PathVariable String name, Model model) {
        model.addAttribute("company", this.companyService.getCompanyByName(name));
        return "company-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable String id) {
        this.companyService.deleteCompany(id);
        return "redirect:/";
    }
}