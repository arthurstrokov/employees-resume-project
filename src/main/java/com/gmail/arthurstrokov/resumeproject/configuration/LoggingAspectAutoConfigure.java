package com.gmail.arthurstrokov.resumeproject.configuration;

import com.gmail.arthurstrokov.resumeproject.aop.LoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LoggingAspect.class)
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAspectAutoConfigure {
    private final LoggingProperties properties;

    public LoggingAspectAutoConfigure(LoggingProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LoggingAspect loggableAspect(){
        return new LoggingAspect(properties.getLoggerName());
    }
}
