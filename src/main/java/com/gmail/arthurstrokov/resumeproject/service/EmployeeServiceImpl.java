package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.EmployeeNotFoundException;
import com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee update(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
