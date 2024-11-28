package com.krz.ems_backend_java.service.impl;

import com.krz.ems_backend_java.dto.EmployeeDto;
import com.krz.ems_backend_java.entity.Employee;
import com.krz.ems_backend_java.exception.DuplicateEntryException;
import com.krz.ems_backend_java.exception.ResourceNotFoundException;
import com.krz.ems_backend_java.mapper.EmployeeMapper;
import com.krz.ems_backend_java.repository.IEmployeeRepository;
import com.krz.ems_backend_java.service.IEmployeeService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        boolean isAlreadyExist = employeeRepository.existsByEmail(employeeDto.getEmail());
        if (isAlreadyExist)
            throw new DuplicateEntryException(employeeDto.getEmail() + " is already exists!");

        Employee employee = EmployeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapEmployeeToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getAnEmployee(Long id) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found with given id: " + id));

        return EmployeeMapper.mapEmployeeToEmployeeDto(foundEmployee);
    }

    @Override
    public EmployeeDto updateAnEmployee(Long id, EmployeeDto employeeDto) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found with given id: " + id));

        foundEmployee.setFullName(employeeDto.getFullName());
        foundEmployee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(foundEmployee);

        return EmployeeMapper.mapEmployeeToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteAnEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapEmployeeToEmployeeDto(employee))
                .collect(Collectors.toList());
    }
}
