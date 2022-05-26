package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(Long id);

    Employee update(EmployeeDTO employeeDTO);

    void deleteById(Long id);

    List<EmployeeDTO> getAllEmployees();
}
