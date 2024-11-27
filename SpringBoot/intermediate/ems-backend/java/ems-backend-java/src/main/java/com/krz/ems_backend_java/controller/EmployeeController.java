package com.krz.ems_backend_java.controller;

import com.krz.ems_backend_java.dto.EmployeeDto;
import com.krz.ems_backend_java.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private IEmployeeService employeeService;

    //Create an employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return ResponseEntity.ok(savedEmployee);
    }
}
