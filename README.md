"# employees-resume-project"

# Resume project

Spring Boot REST service for storing resumes.

# About

Service designed to work with employees resumes.
Contains two profiles for working with (development, production), located in 'spring-boot-cloud-configuration'
application.
'development' use H2 database, 'production' use PostgreSQL database.
Profile can be changed in 'application.yml' properties file.

# How to

## Create

    POST http://localhost:8080/employees
    RESPONSE: HTTP 201 (CREATED)

## Read

    GET http://localhost:8080/employees
    GET http://localhost:8080/employees/{id}
    GET http://localhost:8080/employees/pageable?page=0&size=0&sort=fieldName
    GET http://localhost:8080/employees/filtered?filter=fieldName:value
    RESPONSE: HTTP 200 (OK)

## Update

    PUT http://localhost:8080/employees/{id}
    RESPONSE: HTTP 200 (OK)

## Delete

    DELETE http://localhost:8080/employees/{id}
    RESPONSE: HTTP 204 (NO_CONTENT)
