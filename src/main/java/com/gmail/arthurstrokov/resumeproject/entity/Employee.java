package com.gmail.arthurstrokov.resumeproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "employee")
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
