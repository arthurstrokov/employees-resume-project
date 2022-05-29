package com.gmail.arthurstrokov.resumeproject.mapper;

import com.gmail.arthurstrokov.resumeproject.dto.EmployeeDTO;
import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import org.mapstruct.Mapper;

/**
 * Code generator interface that greatly simplifies
 * the implementation of mappings between Java bean types
 *
 * @author Arthur Strokov
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeDTO employeeDto);

    EmployeeDTO toDTO(Employee employee);
}
