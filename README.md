"# employees-resume-project"

# Resume project

Spring Boot REST service for storing resumes.

# About

Service designed to work with employees resumes.
Contains two profiles for working with (development, production), located in 'spring-boot-cloud-configuration'
application.
'development' use H2 database, server.port:8081;
'production' use PostgreSQL database, server.port: 8080
Profile can be changed in 'application.yml' properties file.

Important: when use PostgreSQL in Employee.java class field 'age' have to be annotated with @Formula(value = "date_part('year', age(birth_date))") instead of @Transient

# How to

## Create

    POST http://localhost:8080/employees
    RESPONSE: HTTP 201 (CREATED)

    Example:
    {
        "firstName": "Arthur",
        "lastName": "Strokov",
        "phone": "375-291555376",
        "age": null,
        "birthDate": "1985-11-01",
        "gender": "M",
        "email": "arthurstrokov@gmail.com"
    }

## Read

    GET http://localhost:8080/employees
    GET http://localhost:8080/employees/{id}
    GET http://localhost:8080/employees/all
    GET http://localhost:8080/employees/pageable?page=0&size=0&sort=fieldName
    GET http://localhost:8080/employees/filtered?search=fieldName:value
    GET http://localhost:8080/employees/?page=0&size=0&sort=fieldValue&search=fieldName:value
    GET http://localhost:8080/employees/?search=fieldName:value&page=0&size=0&sort=fieldValue
    RESPONSE: HTTP 200 (OK)

## Update

    PUT http://localhost:8080/employees/{id}
    RESPONSE: HTTP 200 (OK)

    Example:
    {
    "id": 1
    "firstName": "Arthur",
    "lastName": "Strokov",
    "phone": "375-291555376",
    "age": null,
    "birthDate": "1985-11-01",
    "gender": "M",
    "email": "arthurstrokov@gmail.com"
    }

## Delete

    DELETE http://localhost:8080/employees/{id}
    RESPONSE: HTTP 204 (NO_CONTENT)
