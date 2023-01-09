package com.workflow.runtime.impl;


import com.workflow.bpmn.ActionNode;
import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;
import com.workflow.common.ElementContainer;

import java.util.Map;


public class ServiceTaskProcessRuntime extends BaseProcessRuntime {



    protected void execute(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context) {
        ActionNode actionNode = ((ActionNode) flowNode);
        try {
            execute(process, actionNode.getAction().getActionHandle().getMethod(), context);
        } catch (Exception e) {
            throw new RuntimeException("节点执行异常", e);
        }
    }

}
