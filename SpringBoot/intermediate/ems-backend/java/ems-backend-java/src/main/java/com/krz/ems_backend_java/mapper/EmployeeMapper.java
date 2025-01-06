package com.krz.ems_backend_java.mapper;

import com.krz.ems_backend_java.dto.EmployeeDto;
import com.krz.ems_backend_java.entity.Employee;

public class EmployeeMapper {

    public static Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFullName(),
                employeeDto.getEmail()
        );
    }

    public static EmployeeDto mapEmployeeToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFullName(),
                employee.getEmail()
        );
    }
}
