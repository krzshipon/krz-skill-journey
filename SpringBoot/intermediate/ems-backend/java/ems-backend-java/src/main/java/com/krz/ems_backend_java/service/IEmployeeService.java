package com.krz.ems_backend_java.service;

import java.util.List;

import com.krz.ems_backend_java.dto.EmployeeDto;

public interface IEmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getAnEmployee(Long id);
    EmployeeDto updateAnEmployee(Long id, EmployeeDto employeeDto);
    void deleteAnEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
}
