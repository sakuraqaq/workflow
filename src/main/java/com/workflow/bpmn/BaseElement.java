package com.workflow.bpmn;

import com.workflow.common.Element;

public abstract class BaseElement implements Element {


    private String id;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
