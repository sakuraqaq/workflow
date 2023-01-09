package com.workflow.common.impl;

import com.workflow.bpmn.BaseElement;
import com.workflow.common.IAction;
import com.workflow.common.IActionHandle;

public class Action extends BaseElement implements IAction {

    private IActionHandle actionHandle;

    private String type;

    public void setActionHandle(IActionHandle actionHandle) {
        this.actionHandle = actionHandle;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public IActionHandle getActionHandle() {
        return this.actionHandle;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
