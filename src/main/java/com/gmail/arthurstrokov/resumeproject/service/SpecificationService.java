package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface SpecificationService {
    Specification<Employee> employeeRequestToSpecification(String filter);
}
