package com.gmail.arthurstrokov.resumeproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logging aspect
 * logging by levels: service, controller, repository
 *
 * @author Arthur Strokov
 */
@Aspect
public class LoggingAspect {
    private Logger log;

    public LoggingAspect(String loggerName) {
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    /**
     * Describe witch packages should be logged.
     */
    @Pointcut("execution(* com.gmail.arthurstrokov.resumeproject.service.EmployeeService.*(..)) ||" +
            "execution(* com.gmail.arthurstrokov.resumeproject.controller.EmployeeController.*(..)) ||" +
            "execution(* com.gmail.arthurstrokov.resumeproject.repository.EmployeeRepository.*(..))"
    )
    public void executeLogging() {
    }

    /**
     * Log incoming args
     */
    @Before("executeLogging()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        log.info("Before method: {}", joinPoint.getSignature());
        log.info("Args: {}", joinPoint.getArgs());
    }

    /**
     * Log returning values
     */
    @AfterReturning(pointcut = "executeLogging()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        log.info("After method: {}", joinPoint.getSignature());
        if (result != null) {
            log.info("return value : {}", result);
        } else {
            log.info("with null as return value.");
        }
    }
}
