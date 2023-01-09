package com.workflow.config;

import com.workflow.service.SingLoopProcessService;
import com.workflow.service.TestProcessService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class WorkflowClassConfig {

    @Bean
    public TestProcessService testProcessService(){
        return new TestProcessService();
    }

    @Bean
    public SingLoopProcessService singLoopProcessService(){
        return new SingLoopProcessService();
    }
}
