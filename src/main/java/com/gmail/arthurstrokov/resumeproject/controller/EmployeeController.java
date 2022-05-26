package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
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
     * Get all employees
     *
     * @return employees list
     */
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees();
    }

    /**
     * Get pageable list of employees
     *
     * @param pageable etc. http://localhost:8080/employeesListPageable?page=0&size=3&sort=name
     * @return Sorted pageable list of employees
     */
    @GetMapping(value = "/employeesListPageable")
    ResponseEntity<Page<EmployeeDTO>> employeesPageable(Pageable pageable) {
        try {
            Page<EmployeeDTO> employeesPageable = service.getEmployeesPageable(pageable);
            return new ResponseEntity<>(employeesPageable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //  or throw new PageNotFoundException();
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
