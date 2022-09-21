package com.example.lab3_gtic.controller;

import com.example.lab3_gtic.repository.DepartmentRepository;
import com.example.lab3_gtic.repository.EmployeeRepository;
import com.example.lab3_gtic.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    @GetMapping(value = {"","/list"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeeRepository.findAll());
        model.addAttribute("listaJobs", jobRepository.findAll());
        model.addAttribute("listaDepartments", departmentRepository.findAll());
        return "employee/lista";
    }
}
