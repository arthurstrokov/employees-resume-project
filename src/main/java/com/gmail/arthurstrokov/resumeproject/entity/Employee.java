package com.gmail.arthurstrokov.resumeproject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Entity model for storing in database
 *
 * @author Arthur Strokov
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required. Enter your first name")
    private String firstName;
    @NotBlank(message = "Last name is required. Enter your last name")
    private String lastName;
    @NotBlank(message = "Phone number is required. Enter your phone number")
    private String phone;
    @NotBlank(message = "Email is required. Enter your email")
    @Email
    @Column(unique = true)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id)
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(phone, employee.phone)
                && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, email);
    }
}
