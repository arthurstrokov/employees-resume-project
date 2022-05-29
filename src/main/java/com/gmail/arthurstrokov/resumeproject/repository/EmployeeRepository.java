package com.gmail.arthurstrokov.resumeproject.repository;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for using relative databases
 *
 * @author Arthur Strokov
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    boolean existsByEmail(String email);
}
