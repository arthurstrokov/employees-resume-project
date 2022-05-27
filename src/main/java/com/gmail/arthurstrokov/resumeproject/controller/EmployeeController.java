package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Employee employee = service.save(employeeDTO);
        return new ResponseEntity<>(mapper.toDTO(employee), HttpStatus.CREATED);
    }

    /**
     * Find employee by id
     *
     * @param id employee id
     * @return employee
     */
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") Long id) {
        EmployeeDTO employeeDTO = service.findById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = service.getAllEmployees();
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
            Page<EmployeeDTO> employeesPageable = service.getEmployeesPageable(pageable);
            return new ResponseEntity<>(employeesPageable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        Employee employee = service.update(employeeDTO, id);
        return new ResponseEntity<>(mapper.toDTO(employee), HttpStatus.OK);
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
