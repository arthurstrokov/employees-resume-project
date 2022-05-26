package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
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

    /**
     * Create new employee method
     *
     * @param employeeDTO Employee
     * @return employee
     */
    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        return repository.save(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
    }

    /**
     * Find employee by id
     *
     * @param id employee id
     * @return employee
     */
    @Override
    public EmployeeDTO findById(Long id) {
        return repository.findById(id)
                .map(EmployeeMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
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
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return employees.stream().map(EmployeeMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    /**
     * Get pageable list of employees
     *
     * @param pageable pageable
     * @return Sorted pageable list of employees
     */
    @Override
    public Page<EmployeeDTO> getEmployeesPageable(Pageable pageable) {
        Page<Employee> employees = repository.findAll(pageable);
        return employees.map(EmployeeMapper.INSTANCE::toDTO);
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
