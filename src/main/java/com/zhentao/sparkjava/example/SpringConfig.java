package com.zhentao.sparkjava.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ExampleService exampleService() {
        return new ExampleService();
    }

    @Bean
    public MySparkApplication mySparkApplication() {
        return new MySparkApplication(exampleService());
    }
}
