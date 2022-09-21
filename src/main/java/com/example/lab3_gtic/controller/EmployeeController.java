package com.example.lab3_gtic.controller;

import com.example.lab3_gtic.entity.Employee;
import com.example.lab3_gtic.repository.DepartmentRepository;
import com.example.lab3_gtic.repository.EmployeeRepository;
import com.example.lab3_gtic.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    JobRepository jobRepository;
    DepartmentRepository departmentRepository;


    @GetMapping(value = {"","/list"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeeRepository.findAll());
        model.addAttribute("listaJobs", jobRepository.findAll());
        model.addAttribute("listaDepartments", departmentRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees") Employee employees, Model model) {
        model.addAttribute("listaJobs", jobRepository.findAll());
        model.addAttribute("listaJefes", getListaJefes());
        model.addAttribute("listaDepartments", departmentRepository.findAll());
        return "employee/form";
    }

    public List<Employee> getListaJefes() {
        List<Employee> listaJefes = employeeRepository.findAll();
        Employee e = new Employee();
        e.setId(0);
        e.setFirstName("--No tiene Jefe--");
        listaJefes.add(0, e);
        return listaJefes;
    }

    @PostMapping("/save")
    public String guardarEmpleado(Employee employee, RedirectAttributes attr) {
        employee.setHireDate(Instant.from(LocalDateTime.now()));
        attr.addFlashAttribute("msg", "Empleado creado exitosamente");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model, @RequestParam("id") int id) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("employee", optional.get());
            model.addAttribute("listaJefes", getListaJefes());
            model.addAttribute("listaJobs", jobRepository.findAll());
            model.addAttribute("listaDepartments", departmentRepository.findAll());
            return "employee/editFrm";
        } else {
            return "redirect:/employee";
        }
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }
        attr.addFlashAttribute("msg","Employee borrado exitosamente");
        return "redirect:/employee";
    }
}
