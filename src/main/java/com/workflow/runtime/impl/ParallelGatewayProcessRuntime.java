package com.workflow.runtime.impl;

import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;
import com.workflow.bpmn.ParallelGateway;
import com.workflow.common.ElementContainer;


import java.util.List;
import java.util.Map;

public class ParallelGatewayProcessRuntime extends BaseProcessRuntime {


    protected void execute(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context) {
        String nodeId = process.getNodeId();//上一个完成的节点
        ParallelGateway parallelGateway = (ParallelGateway) flowNode;
        parallelGateway.addFinishNode(nodeId);
        if (parallelGateway.getFinishNode().size() == parallelGateway.getSourceRefs().size()) {
            try {
                execute(process, "parallelGateway", context);
            } catch (Exception e) {
                throw new RuntimeException("并行网关任务执行异常", e);
            }
        }
    }

    public void executeNext(List<FlowNode> nextNodes, FlowNode nextNode, Map<String, FlowNode> flowNodeMap) {

        List<String> targetRefs = ((ParallelGateway) nextNode).getTargetRefs();
        if (targetRefs != null && !targetRefs.isEmpty()) {
            nextNodes.clear();
            targetRefs.forEach(s -> nextNodes.add(flowNodeMap.get(s)));
        }
    }
}
