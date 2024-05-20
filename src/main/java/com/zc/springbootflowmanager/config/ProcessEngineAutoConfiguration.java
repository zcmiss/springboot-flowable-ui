package com.zc.springbootflowmanager.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessEngineAutoConfiguration {

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        return new SpringProcessEngineConfiguration();
    }

    @Bean
    public org.flowable.spring.boot.ProcessEngineAutoConfiguration.ProcessEngineAppConfiguration processAppEngineConfigurationConfigurer() {
        return new org.flowable.spring.boot.ProcessEngineAutoConfiguration.ProcessEngineAppConfiguration();
    }
}
