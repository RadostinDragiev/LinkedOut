package com.linkedout.web;

import com.linkedout.model.binding.EmployeeAddBindingModel;
import com.linkedout.model.binding.EmployeeBindingModel;
import com.linkedout.model.service.EmployeeServiceModel;
import com.linkedout.service.CompanyService;
import com.linkedout.service.EmployeeService;
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

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAddEmployee(Model model) {
        if (!model.containsAttribute("employeeAddBindingModel")) {
            model.addAttribute("employeeAddBindingModel", new EmployeeAddBindingModel());
        }
        model.addAttribute("companies", this.companyService.getAllCompanyNames());
        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employeeAddBindingModel") EmployeeAddBindingModel employeeAddBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeAddBindingModel", bindingResult);
            return "redirect:/employees/add";
        }

        this.employeeService.addEmployee(this.modelMapper.map(employeeAddBindingModel, EmployeeServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees",
                Arrays.stream(this.modelMapper.map(this.employeeService.getAllEmployees(), EmployeeServiceModel[].class)).collect(Collectors.toList()));
        return "employee-all";
    }

    @GetMapping("/details/{id}")
    public String getEmployeeDetails(@PathVariable String id, Model model) {
        model.addAttribute("employee", this.modelMapper.map(this.employeeService.getEmployeeById(id), EmployeeBindingModel.class));
        return "employee-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}