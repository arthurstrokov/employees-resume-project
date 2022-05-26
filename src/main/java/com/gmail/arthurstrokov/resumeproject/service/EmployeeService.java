package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(String id);

    Employee update(EmployeeDTO employeeDTO);

    void deleteById(String id);
}
