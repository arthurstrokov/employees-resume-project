package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.ResourceAlreadyExistsException;
import com.gmail.arthurstrokov.resumeproject.exceptions.ResourceNotFoundException;
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
    private final EmployeeMapper mapper;

    /**
     * Check if email already exist
     *
     * @param email email
     * @return boolean
     */
    @Override
    public boolean ifExists(String email) {
        return repository.existsByEmail(email);
    }

    /**
     * Create new employee method
     *
     * @param employeeDTO EmployeeDTO
     * @return employee
     */
    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEntity(employeeDTO);
        if (ifExists(employee.getEmail())) {
            throw new ResourceAlreadyExistsException(employee.getEmail());
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
    public EmployeeDTO findById(Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toDTO(employee);
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return employees.stream().map(mapper::toDTO).collect(Collectors.toList());
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
        return employees.map(mapper::toDTO);
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
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setPhone(employeeDTO.getPhone());
                    employee.setEmail(employeeDTO.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    employeeDTO.setId(id);
                    return repository.save(mapper.toEntity(employeeDTO));
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
