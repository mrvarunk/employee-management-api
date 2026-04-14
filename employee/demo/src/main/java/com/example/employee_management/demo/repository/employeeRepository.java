package com.example.employee_management.demo.repository;

import com.example.employee_management.demo.model.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<employee,Long> {
}
