package com.workflow.bpmn;


import com.workflow.common.IAction;
import com.workflow.process.converter.parser.HasAction;

public class ExclusiveGateway extends GatewayNode implements HasAction {

    private IAction action;

    @Override
    public IAction getAction() {
        return action;
    }

    @Override
    public void setAction(IAction action) {
        this.action = action;
    }

}
