package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.exceptions.PageNotFoundException;
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

    /**
     * Create new employee method
     *
     * @param employeeDTO Employee
     * @return employee
     */
    @PostMapping
    public Employee save(@RequestBody EmployeeDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    /**
     * Find employee by id
     *
     * @param id employee id
     * @return employee
     */
    @GetMapping("{id}")
    public EmployeeDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    /**
     * Find employee by id
     *
     * @param email employee email
     * @return Employee found by email
     */
    @GetMapping
    Employee findByEmail(@RequestParam("email") String email) {
        return service.findByEmail(email);
    }

    /**
     * Get all employees
     *
     * @return employees list
     */
    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees();
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
    public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        return service.update(employeeDTO, id);
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
