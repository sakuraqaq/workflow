package com.workflow.bpmn;

import com.workflow.common.AbstractFlowModel;
import com.workflow.common.TransitionNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BpmnModel extends AbstractFlowModel<FlowNode> {

    private final List<Process> processes = new ArrayList<>(1);

    public void addProcesses(Process process){
        processes.add(process);
    }

    public Process getProcess(){
        return processes.get(0);
    }


    @Override
    public List<FlowNode> getRuntimeNodes() {
        return getAllNodes();
    }

    @Override
    public List<TransitionNode> getTransitionNodes() {
        return processes.stream().map(Process::getFlowElements).flatMap(Collection::stream)
                .filter(flowElement -> flowElement instanceof TransitionNode)
                .map(flowElement -> (TransitionNode) flowElement)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlowNode> getAllNodes() {
        return processes.stream().map(Process::getAllNodes)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public void addNode(FlowNode node) {
        getAllNodes().add(node);
    }

    @Override
    public FlowNode getNode(String id) {
        return getAllNodes().stream().filter(node -> id.equals(node.getId())).findFirst()
                .orElseThrow(() -> new RuntimeException("未找到节点, 节点id是: " + id));
    }

    @Override
    public FlowNode getStartNode() {
        return getAllNodes().stream()
                .filter(node -> node instanceof StartEvent)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("未找到开始节点"));
    }

    @Override
    public FlowNode getEndNode() {
        return getAllNodes().stream()
                .filter(node -> node instanceof EndEvent)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("未找到结束节点"));
    }
}
