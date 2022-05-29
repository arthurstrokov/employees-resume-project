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

    List<EmployeeDTO> getAll();

    Page<EmployeeDTO> getAllPageable(Pageable pageable);

    Employee update(EmployeeDTO employee, Long id);

    void deleteById(Long id);
}
