package com.workflow.bpmn;

import com.workflow.common.ElementContainer;
import com.workflow.common.IVar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Process extends BaseElement implements ElementContainer<FlowElement, FlowNode> {

    private String name;

    /**
     * 所有节点
     */
    private List<FlowElement> flowElements = new ArrayList<>();

    /**
     * 暂时没有用
     */
    private List<IVar> vars = new ArrayList<>();

    private Boolean isClosed = false;

    private Boolean isExecutable;

    /**
     * 当前执行的节点
     */
    private String nodeId;

    /**
     * 下一个执行的节点
     */
    private String nextTargetRef;

    private Serializable workflowId;

    @Override
    public Serializable getWorkflowId() {
        return workflowId;
    }

    @Override
    public void setWorkflowId(Serializable workflowId) {
        this.workflowId = workflowId;
    }

    public void setNextTargetRef(String nextTargetRef) {
        this.nextTargetRef = nextTargetRef;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    @Override
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlowElement> getFlowElements() {
        return flowElements;
    }

    public void setFlowElements(List<FlowElement> flowElements) {
        this.flowElements = flowElements;
    }

    public List<IVar> getVars() {
        return vars;
    }

    public void addVar(IVar var) {
        this.vars.add(var);
    }

    public Boolean getExecutable() {
        return isExecutable;
    }

    public void setExecutable(Boolean executable) {
        isExecutable = executable;
    }

    @Override
    public Boolean isClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTag() {
        return null;
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
