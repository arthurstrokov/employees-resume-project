package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.EmployeeNotFoundException;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
    }

    @Override
    public EmployeeDTO findById(String id) {
        return repository.findById(id)
                .map(EmployeeMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee update(EmployeeDTO employeeDTO) {
        return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
