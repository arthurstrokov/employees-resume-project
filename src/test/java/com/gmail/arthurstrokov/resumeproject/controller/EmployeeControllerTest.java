package com.gmail.arthurstrokov.resumeproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import com.gmail.arthurstrokov.resumeproject.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @MockBean
    EmployeeService employeeService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    private List<EmployeeDTO> employeeDTOList;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Arthur")
                .lastName("Strokov")
                .phone("375291555376")
                .email("arthurstrokov@gmail.com")
                .build();
        employeeDTO = EmployeeDTO.builder()
                .firstName("Arthur")
                .lastName("Strokov")
                .phone("375291555376")
                .email("arthurstrokov@gmail.com")
                .build();
        employeeDTOList = new ArrayList<>();
        employeeDTOList.add(employeeDTO);
    }

    @AfterEach
    void tearDown() {
        employee = null;
    }

    @Test
    void save() throws Exception {
        String json = objectMapper.writeValueAsString(employee);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        when(employeeService.save(any(EmployeeDTO.class))).thenReturn(employee);
        verify(employeeService, times(1)).save(any(EmployeeDTO.class));
    }

    @Test
    void findById() throws Exception {
        when(employeeService.findById(employee.getId())).thenReturn(employeeDTO);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeDTOList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(employeeService).getAllEmployees();
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void employeesPageable() throws Exception {
        when(employeeService.getEmployeesPageable(any(Pageable.class))).thenReturn(Page.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees?page=1&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        String json = objectMapper.writeValueAsString(employee);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        Mockito.when(employeeService.update(any(EmployeeDTO.class), anyLong())).thenReturn(employee);
        verify(employeeService, times(1)).update(any(EmployeeDTO.class), anyLong());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
        verify(employeeService, times(1)).deleteById(1L);
    }
}