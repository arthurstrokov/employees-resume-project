package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    boolean ifExists(String value);

    Employee save(Employee employee);

    Employee findById(Long id);

    Employee findByEmail(String email);

    List<Employee> getAllEmployees();

    Page<Employee> getEmployeesPageable(Pageable pageable);

    Employee update(Employee employee, Long id);

    void deleteById(Long id);
}
