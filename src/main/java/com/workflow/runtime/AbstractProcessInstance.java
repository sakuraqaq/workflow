package com.workflow.runtime;


import java.util.Map;

public class AbstractProcessInstance implements ProcessInstance {

    public Boolean start(Map<String, Object> context){
        return true;
    }

    public Boolean parallelGateway(Map<String, Object> context){
        return true;
    }

    public Boolean serviceTask(Map<String, Object> context){
        return true;
    }

    public Boolean end(Map<String, Object> context){
        return true;
    }
}
