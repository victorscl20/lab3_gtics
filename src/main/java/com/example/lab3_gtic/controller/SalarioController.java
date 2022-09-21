package com.example.lab3_gtic.controller;

import com.example.lab3_gtic.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("reportes")
public class SalarioController {
    @Autowired
    JobRepository jobRepository;

    @GetMapping("salario")
    public String salario(Model model) {
        model.addAttribute("listaSalario",jobRepository.obtenerMySalarioDto());
        return "Search/reporteSalario";
    }

    @GetMapping("pordepartamento")
    public String salariopordepartamento(Model model) {
        model.addAttribute("listaSalarioPorDepa",jobRepository.obtenerMySalarioxDptoDto());
        return "Search/reporteSalarioxDepa";
    }

}
