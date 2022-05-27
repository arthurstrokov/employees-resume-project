package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.EmployeeAlreadyExistsException;
import com.gmail.arthurstrokov.resumeproject.exceptions.EmployeeNotFoundException;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee service
 *
 * @author Arthur Strokov
 */
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public boolean ifExists(String value) {
        return repository.existsByEmail(value);
    }

    /**
     * Create new employee method
     *
     * @param employeeDTO Employee
     * @return employee
     */
    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
        if (ifExists(employee.getEmail())) {
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        } else {
            return repository.save(employee);
        }
    }

    /**
     * Find employee by id
     *
     * @param id employee id
     * @return employee
     */
    @Override
    public Employee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Find employee by email
     *
     * @param email employee id
     * @return employee
     */
    @Override
    public Employee findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    /**
     * Get pageable list of employees
     *
     * @param pageable pageable
     * @return Sorted pageable list of employees
     */
    @Override
    public Page<Employee> getEmployeesPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Update employee
     *
     * @param employeeDTO EmployeeDTO
     * @param id          employee id
     * @return employee
     */
    @Override
    public Employee update(EmployeeDTO employeeDTO, Long id) {
        return repository.findById(id).map(employee -> {
            EmployeeMapper.INSTANCE.toEntity(employeeDTO);
            return repository.save(employee);
        }).orElseGet(() -> {
            employeeDTO.setId(id);
            return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
        });
    }

    /**
     * Delete employee
     *
     * @param id employee id
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
