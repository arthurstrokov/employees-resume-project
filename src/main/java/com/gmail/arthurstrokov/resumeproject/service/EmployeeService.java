package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    boolean ifExists(String value);

    Employee save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(Long id);

    Employee findByEmail(String email);

    List<EmployeeDTO> getAllEmployees();

    Page<EmployeeDTO> getEmployeesPageable(Pageable pageable);

    Employee update(EmployeeDTO employeeDTO, Long id);

    void deleteById(Long id);
}
