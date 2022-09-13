package com.gmail.arthurstrokov.resumeproject.repository;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.entity.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    TestEntityManager testEntityManager;
    private Employee employee;
    private Employee savedEmployee;

    @Test
    void exist() {
        assertNotNull(employeeRepository);
        assertNotNull(entityManager);
        assertNotNull(testEntityManager);
    }

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Arthur")
                .lastName("Strokov")
                .phone("375-291555376")
                .birthDate(new Date())
                .gender(Gender.MALE)
                .email("arthurstrokov@gmail.com")
                .build();

        savedEmployee = employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
    }

    @Test
    void existsByEmail() {
        assertTrue(employeeRepository.existsByEmail("arthurstrokov@gmail.com"));
    }

    @Test
    void saveEmployee() {
        assertNotNull(employeeRepository.findById(1L));
        assertNotNull(savedEmployee.getId());
    }
}
