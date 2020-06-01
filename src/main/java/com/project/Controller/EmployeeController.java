package com.project.Controller;

import com.project.pojomodel.Employee;
import com.project.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EmployeeController{
    @Autowired
    private EmployeeRepo empRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return empRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return empRepo.findById(employeeId).get();
    }
}
