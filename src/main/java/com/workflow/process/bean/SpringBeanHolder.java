package com.workflow.process.bean;

import org.springframework.context.ApplicationContext;

public class SpringBeanHolder {

    private static ApplicationContext context;

    public static SpringBeanHolder of(ApplicationContext applicationContext) {
        SpringBeanHolder springBeanHolder = new SpringBeanHolder();
        SpringBeanHolder.context = applicationContext;
        return springBeanHolder;
    }


    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) context.getBean(name);
    }


    public <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }


    public boolean containsBean(String name) {
        return context.containsBean(name);
    }

}
