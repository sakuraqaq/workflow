package com.workflow.bpmn;

import com.workflow.common.IAction;

public class ServiceTask extends Task {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public IAction getAction() {
        return super.getAction();
    }

    @Override
    public void setAction(IAction action) {
        super.setAction(action);
    }
}
