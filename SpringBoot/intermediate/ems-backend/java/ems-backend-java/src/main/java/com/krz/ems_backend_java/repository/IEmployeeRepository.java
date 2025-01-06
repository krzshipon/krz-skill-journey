package com.krz.ems_backend_java.repository;

import com.krz.ems_backend_java.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
  boolean existsByEmail(String email);
}
