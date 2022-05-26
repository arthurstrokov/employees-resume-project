package com.gmail.arthurstrokov.resumeproject.controller;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping
    public Employee save(@RequestBody EmployeeDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    @GetMapping("{id}")
    public EmployeeDTO getByEmployeeId(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return service.update(employeeDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
