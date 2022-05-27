package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.PageNotFoundException;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee controller
 *
 * @author Arthur Strokov
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeMapper mapper;

    /**
     * Create new employee method
     *
     * @param employeeDTO Employee
     * @return employee
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEntity(employeeDTO);
        Employee savedEmployee = service.save(employee);
        return new ResponseEntity<>(mapper.toDTO(savedEmployee), HttpStatus.CREATED);
    }

    /**
     * Find employee by id
     *
     * @param id employee id
     * @return employee
     */
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") Long id) {
        Employee employee = service.findById(id);
        EmployeeDTO employeeDTO = mapper.toDTO(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    /**
     * Find employee by id
     *
     * @param email employee email
     * @return Employee found by email
     */
    @GetMapping
    ResponseEntity<EmployeeDTO> findByEmail(@RequestParam("email") String email) {
        Employee employee = service.findByEmail(email);
        EmployeeDTO employeeDTO = mapper.toDTO(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = service.getAllEmployees()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    /**
     * Get pageable list of employees
     * localhost:8080/employees/pageable?page=0&size=0&sort=fieldName
     *
     * @param pageable pageable
     * @return Sorted pageable list of employees
     */
    @GetMapping(value = "/pageable")
    ResponseEntity<Page<EmployeeDTO>> employeesPageable(Pageable pageable) {
        try {
            Page<Employee> employees = service.getEmployeesPageable(pageable);
            Page<EmployeeDTO> employeesPageable = employees.map(mapper::toDTO);
            return new ResponseEntity<>(employeesPageable, HttpStatus.OK);
        } catch (Exception e) {
            throw new PageNotFoundException();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update employee
     *
     * @param employeeDTO EmployeeDTO
     * @param id          employee id
     * @return employee
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        Employee newEmployee = mapper.toEntity(employeeDTO);
        Employee employee = service.update(newEmployee, id);
        EmployeeDTO updatedEmployee = mapper.toDTO(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    /**
     * Delete employee
     *
     * @param id employee id
     * @return ResponseEntity.noContent().build()
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
