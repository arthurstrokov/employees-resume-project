package com.gmail.arthurstrokov.resumeproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
public class LoggingAspect {
    private final Logger log;

    public LoggingAspect(String loggerName) {
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    @Pointcut("within(com.gmail.arthurstrokov.resumeproject.service..*)")
    public void executeLogging() {
    }


    @Around("executeLogging()")
    public Object executeLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Hey");
        Object value = proceedingJoinPoint.proceed();
        log.info("dude");
        return value;
    }
}
