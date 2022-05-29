package com.gmail.arthurstrokov.resumeproject.filter;

import com.gmail.arthurstrokov.resumeproject.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmployeeSpecificationService
 *
 * @author Arthur Strokov
 */
@RequiredArgsConstructor
@Service
public class EmployeeSpecificationService {
    private final EmployeeSpecificationsBuilder builder;

    /**
     * Convert String request filter to Specification
     *
     * @param filter request filter
     * @return Specification
     * @see Specification
     */
    public Specification<Employee> getEmployeeSpecification(String filter) {
        Pattern pattern = Pattern.compile("(\\w+?)(:)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }
}
