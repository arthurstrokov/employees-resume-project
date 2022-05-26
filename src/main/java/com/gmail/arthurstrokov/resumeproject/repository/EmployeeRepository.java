package com.gmail.arthurstrokov.resumeproject.repository;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
