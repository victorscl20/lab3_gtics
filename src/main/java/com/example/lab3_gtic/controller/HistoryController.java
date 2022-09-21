package com.example.lab3_gtic.controller;

import com.example.lab3_gtic.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping(value={"","/"})
    public String historialEmpleados(Model model){
        model.addAttribute("listaHistorial",employeeRepository.obtenerHistory());
        return "/historial/lista";
    }


}
