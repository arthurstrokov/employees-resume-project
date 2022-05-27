package com.gmail.arthurstrokov.resumeproject.service;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeServiceImplTest {
    @MockBean
    EmployeeRepository employeeRepository;
    @Autowired
    @InjectMocks
    EmployeeServiceImpl employeeService;
    private List<Employee> employeeList;
    private List<EmployeeDTO> employeeDTOList;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employeeList = new ArrayList<>();
        employeeDTOList = new ArrayList<>();
        employee = Employee.builder()
                .id(1L)
                .firstName("Arthur")
                .lastName("Strokov")
                .phone("375291555376")
                .email("arthurstrokov@gmail.com")
                .build();
        employeeDTO = EmployeeDTO.builder()
                .id(1L)
                .firstName("Arthur")
                .lastName("Strokov")
                .phone("375291555376")
                .email("arthurstrokov@gmail.com")
                .build();
        employeeList.add(employee);
        employeeDTOList.add(employeeDTO);
    }

    @AfterEach
    void tearDown() {
        employeeList = null;
        employee = null;
    }

    @Test
    void ifExists() {
        when(employeeRepository.existsByEmail(any())).thenReturn(true);
        assertTrue(employeeService.ifExists("arthurstrokov@gmail.com"));
    }

    @Test
    void save() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        employeeService.save(employeeDTO);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void findById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
        assertThat(employeeService.findById(employeeDTO.getId())).isEqualTo(employeeDTO);
        assertDoesNotThrow(() -> employeeService.findById(4L));
    }


    @Test
    void getAllEmployees() {
        employeeRepository.save(employee);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertEquals(employees, employeeDTOList);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeesPageable() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("email"));
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        Page<EmployeeDTO> employeesPageable = employeeService.getEmployeesPageable(pageable);
        assertNotNull(employeesPageable);
    }

    @Test
    void update() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee saved = employeeService.update(employeeDTO, 1L);
        assertEquals(saved, employee);
    }

    @Test
    void deleteById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
        assertDoesNotThrow(() -> employeeService.deleteById(anyLong()));
    }
}