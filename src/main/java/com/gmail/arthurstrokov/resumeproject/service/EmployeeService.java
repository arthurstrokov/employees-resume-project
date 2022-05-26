package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(Long id);

    Employee update(Employee employee);

    void deleteById(Long id);
}
