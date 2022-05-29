package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.ResourceAlreadyExistsException;
import com.gmail.arthurstrokov.resumeproject.exceptions.ResourceNotFoundException;
import com.gmail.arthurstrokov.resumeproject.mapper.EmployeeMapper;
import com.gmail.arthurstrokov.resumeproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<EmployeeDTO> save(@Valid @RequestBody EmployeeDTO employeeDTO) {
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
        try {
            EmployeeDTO employeeDTO = service.findById(id);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.valueOf(e));
        }
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        try {
            List<EmployeeDTO> employeeDTOList = service.getAll();
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.valueOf(e));
        }
    }

    /**
     * Get pageable list of employees
     * localhost:8080/employees/pageable?page=0&size=0&sort=fieldName
     *
     * @param pageable pageable
     * @return Sorted pageable list of employees
     */
    @GetMapping(value = "/pageable")
    ResponseEntity<Page<EmployeeDTO>> getAllPageable(@PageableDefault(value = 1, page = 1) Pageable pageable) {
        try {
            Page<EmployeeDTO> employeesPageable = service.getAllPageable(pageable);
            return new ResponseEntity<>(employeesPageable, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.valueOf(e));
        }
    }

    /**
     * Get filtered list of employees
     * localhost:8080/employees/filtered?filter=fieldName:value
     *
     * @param filter filter
     * @return filtered list of employees
     */
    @GetMapping("/filtered")
    public ResponseEntity<List<EmployeeDTO>> getAllFiltered(@RequestParam(value = "search", required = false) String filter) {
        try {
            List<EmployeeDTO> employeeDTOList = service.getAllByFilter(filter);
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(String.valueOf(e));
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
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Long id) {
        try {
            Employee employee = service.update(employeeDTO, id);
            return new ResponseEntity<>(mapper.toDTO(employee), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceAlreadyExistsException(employeeDTO.toString());
        }
    }

    /**
     * Delete employee
     *
     * @param id employee id
     * @return ResponseEntity.noContent().build()
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
