package com.gmail.arthurstrokov.resumeproject.repository;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for using relative databases
 *
 * @author Arthur Strokov
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);
}
