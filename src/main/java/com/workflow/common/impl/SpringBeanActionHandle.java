package com.workflow.common.impl;

import com.workflow.bpmn.BaseElement;
import com.workflow.common.IActionHandle;

public class SpringBeanActionHandle extends BaseElement implements IActionHandle {

    private String bean;

    private String clazz;

    private String method = "execute";

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }
}
