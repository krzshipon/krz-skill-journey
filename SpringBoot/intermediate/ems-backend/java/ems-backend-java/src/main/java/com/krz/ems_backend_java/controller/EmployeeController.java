package com.krz.ems_backend_java.controller;

import com.krz.ems_backend_java.dto.EmployeeDto;
import com.krz.ems_backend_java.service.IEmployeeService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private IEmployeeService employeeService;

    // Create an employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(savedEmployee);
    }

    // Get an employee
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getAnEmployee(@PathVariable Long id) {
        EmployeeDto foundEmployee = employeeService.getAnEmployee(id);
        return ResponseEntity.ok(foundEmployee);
    }

    // Update an employee by id
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateAnEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateAnEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    //Delete an employee by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAnEmployeeById(@PathVariable Long id){
        employeeService.deleteAnEmployeeById(id);

        return ResponseEntity.ok("Employee deleted successfully!");
    }

    //Get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        
        return ResponseEntity.ok(employees);
    }
    

}
