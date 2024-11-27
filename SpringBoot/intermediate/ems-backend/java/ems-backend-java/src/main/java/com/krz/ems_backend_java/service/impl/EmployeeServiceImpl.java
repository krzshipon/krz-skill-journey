package com.krz.ems_backend_java.service.impl;

import com.krz.ems_backend_java.dto.EmployeeDto;
import com.krz.ems_backend_java.entity.Employee;
import com.krz.ems_backend_java.mapper.EmployeeMapper;
import com.krz.ems_backend_java.repository.IEmployeeRepository;
import com.krz.ems_backend_java.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapEmployeeToEmployeeDto(savedEmployee);
    }
}
