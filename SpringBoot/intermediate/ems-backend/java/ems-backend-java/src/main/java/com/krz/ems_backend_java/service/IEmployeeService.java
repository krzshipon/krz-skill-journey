package com.krz.ems_backend_java.service;

import com.krz.ems_backend_java.dto.EmployeeDto;

public interface IEmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
