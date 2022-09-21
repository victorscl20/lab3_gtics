package com.example.lab3_gtic.controller;

import com.example.lab3_gtic.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HistoryController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/historial")
    public String listaEmployee(Model model){
        model.addAtribute("listaHistory",employeeRepository.);
        return  "/historial/lista";
    }


}
