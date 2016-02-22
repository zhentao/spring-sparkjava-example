package com.zhentao.sparkjava.example;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;

public class SpringSparkFilter extends SparkFilter {
    public static final String SPRINT_CONFIG_LOCATION_PARAM = "springConfigLocation";

    @Override
    protected SparkApplication getApplication(FilterConfig filterConfig) throws ServletException {
        Class<?>[] configClasses = getConfigClasses(filterConfig);
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(configClasses)) {
            String applicationClassName = filterConfig.getInitParameter(APPLICATION_CLASS_PARAM);

            try {
                return (SparkApplication) context.getBean(Class.forName(applicationClassName));
            } catch (ClassNotFoundException e) {
                throw new ServletException(e);
            }
        }
    }

    private Class<?>[] getConfigClasses(FilterConfig filterConfig) throws ServletException {

        String springConfigLocation = filterConfig.getInitParameter(SPRINT_CONFIG_LOCATION_PARAM);
        if (springConfigLocation == null) {
            throw new ServletException("initParam (springConfigLocation) is not found");
        }
        String[] springConfigs = springConfigLocation.split(",");
        Class<?>[] configClasses = new Class[springConfigs.length];
        for (int i = 0; i < springConfigs.length; i++) {
            String config = springConfigs[i].trim();
            try {
                configClasses[i] = Class.forName(config);
            } catch (ClassNotFoundException e) {
                throw new ServletException(e);
            }
        }
        return configClasses;
    }
}
