package com.workflow.bpmn;

import com.workflow.common.ElementContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 子流程当作一个，新的流程执行，按理说不会影响父流程。这样流程表中需要加入 parentId。
 */
public class SubProcess extends FlowNode implements ElementContainer<FlowElement, FlowNode> {

    private final List<FlowElement> flowElements = new ArrayList<>();

    private String nodeId;

    private String nextTargetRef;

    private String parentId;

    private Boolean isClosed = false;

    private Serializable workflowId;

    private Serializable parentWorkflowId;

    public Serializable getParentWorkflowId() {
        return parentWorkflowId;
    }

    public void setParentWorkflowId(Serializable parentWorkflowId) {
        this.parentWorkflowId = parentWorkflowId;
    }

    @Override
    public Serializable getWorkflowId() {
        return workflowId;
    }

    @Override
    public void setWorkflowId(Serializable workflowId) {
        this.workflowId = workflowId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setNextTargetRef(String nextTargetRef) {
        this.nextTargetRef = nextTargetRef;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    @Override
    public Boolean isClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

   @Override
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    @Override
    public List<FlowElement> getElementAll() {
        return this.flowElements;
    }

    @Override
    public void addElement(FlowElement element) {
        this.flowElements.add(element);
    }

    @Override
    public FlowElement getElement(String id) {
        return flowElements.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("未找到元素,id是: " + id));
    }

    @Override
    public String getNextTargetRef() {
        return nextTargetRef;
    }

    @Override
    public List<FlowNode> getAllNodes() {
        return this.flowElements.stream()
                .filter(e -> e instanceof FlowNode)
                .map(e -> (FlowNode) e)
                .collect(Collectors.toList());
    }

    @Override
    public void addNode(FlowNode node) {
        this.flowElements.add(node);
    }

    @Override
    public FlowNode getNode(String id) {
        return (FlowNode) getElement(id);
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
