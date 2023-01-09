package com.workflow.runtime.impl;

import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;
import com.workflow.bpmn.SubProcess;
import com.workflow.common.ElementContainer;
import com.workflow.jdbc.AbstractJDBCProcessHandler;


import java.io.Serializable;
import java.util.Map;

public class SubProcessRuntime extends BaseProcessRuntime {


    protected void execute(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context) {
        try {

            SubProcess subProcess = (SubProcess) flowNode;
            subProcess.setNodeId(subProcess.getStartNode().getId());
            subProcess.setParentWorkflowId(process.getWorkflowId());
            Serializable workflowId = AbstractJDBCProcessHandler.getInstance(AbstractJDBCProcessHandler.ORM_MYBATIS)
                    .saveProcess(subProcess);
            subProcess.setWorkflowId(workflowId);
            start(subProcess, context);

        } catch (Exception e) {
            throw new RuntimeException("子节点执行异常", e);
        }
    }
}
