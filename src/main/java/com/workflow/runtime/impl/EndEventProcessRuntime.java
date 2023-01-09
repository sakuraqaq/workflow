package com.workflow.runtime.impl;

import com.workflow.bpmn.Process;
import com.workflow.common.ElementContainer;
import com.workflow.process.converter.BpmnModelConverter;
import com.workflow.bpmn.*;

import java.util.Map;

public class EndEventProcessRuntime extends BaseProcessRuntime {


    protected void execute(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context) {
        try {
            if (process instanceof Process) {
                ((Process) process).setClosed(true);
                execute(process, "end", context);
            }
            if (process instanceof SubProcess) {
                SubProcess subProcess = (SubProcess) process;
                subProcess.setClosed(true);
                String parentId = subProcess.getParentId();
                String targetRef = subProcess.getTargetRef();
                BpmnModel bpmnModel = BpmnModelConverter.getModelCache().get(parentId);
                Process parentProcess = bpmnModel.getProcess();
                parentProcess.setNextTargetRef(targetRef);
                parentProcess.setNodeId(targetRef);
                parentProcess.setWorkflowId(subProcess.getParentWorkflowId());
                executeNext(parentProcess, context);
            }
        } catch (Exception e) {
            throw new RuntimeException("结束节点执行异常", e);
        }
    }

}
