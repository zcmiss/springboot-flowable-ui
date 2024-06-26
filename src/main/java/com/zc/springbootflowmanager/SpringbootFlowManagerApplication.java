package com.zc.springbootflowmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zengc
 * @date 2024/05/17
 */
@ComponentScan(basePackages = {"org.flowable.ui.application", "com.zc.springbootflowmanager"})
@SpringBootApplication
public class SpringbootFlowManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFlowManagerApplication.class, args);
    }

}
