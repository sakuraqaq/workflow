package com.workflow.bpmn;

import com.workflow.common.IVar;
import com.workflow.common.TransitionNode;

import java.util.ArrayList;
import java.util.List;

public abstract class FlowNode extends FlowElement implements TransitionNode {

    private String tag;

    private final List<FlowNode> incomingNodes = new ArrayList<>();

    private final List<FlowNode> outgoingNodes = new ArrayList<>();

    private List<SequenceFlow> incomingFlows = new ArrayList<>();

    private List<SequenceFlow> outgoingFlows = new ArrayList<>();

    private List<IVar> vars = new ArrayList<>();

    @Override
    public String getTag() {
        if(tag != null){
            return tag;
        }
        return getId();
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<SequenceFlow> getIncomingFlows() {
        return incomingFlows;
    }

    public List<SequenceFlow> getOutgoingFlows() {
        return outgoingFlows;
    }

    public void addIncomingFlow(SequenceFlow incoming) {
        incomingFlows.add(incoming);
    }

    public void addOutgoingFlow(SequenceFlow outgoing) {
        outgoingFlows.add(outgoing);
    }

    @Override
    public List<FlowNode> getIncomingNodes() {
        return incomingNodes;
    }

    @Override
    public List<FlowNode> getOutgoingNodes() {
        return null;
    }

    public void addIncomingNode(FlowNode incomingNode) {
        this.incomingNodes.add(incomingNode);
    }

    public void addOutgoingNode(FlowNode outgoingNode) {
        this.outgoingNodes.add(outgoingNode);
    }

    public List<IVar> getVars() {
        return vars;
    }

    public void addVars(IVar ivar) {
        this.vars.add(ivar);
    }
}
