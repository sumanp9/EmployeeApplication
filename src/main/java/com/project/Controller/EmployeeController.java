package com.project.Controller;

import com.project.EmployeeDto;
import com.project.pojomodel.*;
import com.project.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Using CrossOrigin annotation to share resource which allows restricted resources to be
// requested from another domain outside the domain which the first resources was served.
@RestController @CrossOrigin(origins = "http://localhost:4200")
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
        System.out.println("Getting employees: "+ empRepo.findAll().size());
        return empRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return empRepo.findById(employeeId).get();
    }
    @PostMapping("/addEmployee")
    public void addEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("The name f new employee is: " + employeeDto.getFirstName());
        if (employeeDto.id == null) {
            Employee emp =  new Employee();
            emp.setFirstName(employeeDto.getFirstName());
            emp.setLastName(employeeDto.getLastName());
            emp.setEmailId(employeeDto.getEmailId());
            emp.setRole(employeeDto.getRole());
            empRepo.save(emp);
        } else{
            try{
                Employee existingEmp = empRepo.findById(employeeDto.getId()).get();
                existingEmp.setFirstName(employeeDto.getFirstName());
                existingEmp.setLastName(employeeDto.getLastName());
                existingEmp.setEmailId(employeeDto.getEmailId());
                existingEmp.setRole(employeeDto.getRole());
                empRepo.save(existingEmp);
            } catch(NullPointerException nullEx) {
                throw new NullPointerException("Unable to find employee with id: "+ employeeDto.getId()+ " "+ nullEx.getMessage());
            }
        }
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long id){
        try {
            Employee employee = empRepo.findById(id).get();
            empRepo.delete(employee);
        } catch (NullPointerException ex) {
            throw new NullPointerException("Unable to find the employee with id: "+ id);
        }
    }
}
