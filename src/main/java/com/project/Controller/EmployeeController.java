package com.project.Controller;

import com.project.pojomodel.*;
import com.project.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController{
    @Autowired
    private EmployeeRepo empRepo;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @GetMapping("/")
    public String homepage() {
        return "<h1>Hello and welcome to the home page</h1>";
    }

    @RequestMapping("/employees")
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return empRepo.findById(employeeId).get();
    }
    //@PostMapping("/employee/")
}
