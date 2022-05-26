package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.EmployeeNotFoundException;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
    }

    @Override
    public EmployeeDTO findById(Long id) {
        return repository.findById(id)
                .map(EmployeeMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee update(EmployeeDTO employeeDTO) {
        return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return employees.stream().map(EmployeeMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
