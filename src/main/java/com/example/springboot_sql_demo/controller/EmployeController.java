package com.example.springboot_sql_demo.controller;
import com.example.springboot_sql_demo.repository.EmployeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeController {

    private final EmployeRepository employeRepository;

    public EmployeController(EmployeRepository employeRepository){
        this.employeRepository = employeRepository;
    }

    @GetMapping("/employes/commandes-france")
    public EmployeRepository getEmployeRepository() {
        return employeRepository;
    }
}