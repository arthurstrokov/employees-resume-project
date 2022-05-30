package com.gmail.arthurstrokov.resumeproject.dto;

import com.gmail.arthurstrokov.resumeproject.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO model for presentation
 *
 * @author Arthur Strokov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private Date birthDate;
    private Gender gender;
    private String email;
}
