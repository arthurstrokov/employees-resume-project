package com.gmail.arthurstrokov.resumeproject.repository;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
