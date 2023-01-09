package com.workflow.service;

import com.workflow.runtime.AbstractProcessInstance;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("singLoop")
public class SingLoopProcessService extends AbstractProcessInstance   {


    public Boolean sing(Map<String, Object> context){
        System.out.println("sing（）");
        return true;
    }

}
