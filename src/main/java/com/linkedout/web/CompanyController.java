package com.linkedout.web;

import com.linkedout.model.binding.CompanyAddBindingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/companies")
public class CompanyController {
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
        return "redirect:/";
    }
}



