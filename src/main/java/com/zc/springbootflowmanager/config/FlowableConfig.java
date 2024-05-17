package com.zc.springbootflowmanager.config;

/**
 * @author zengc
 * @date 2024/05/17
 */
@Configuration
public class FlowableConfig {

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(SpringProcessEngineConfiguration configuration) {
        // 如果需要，自定义配置
        configuration.setAsyncExecutorActivate(false);
        return configuration;
    }
}