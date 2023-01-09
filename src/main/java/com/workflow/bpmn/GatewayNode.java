package com.workflow.bpmn;

import java.util.List;

public abstract class GatewayNode extends FlowNode {

    private List<String> sourceRefs;

    private List<String> targetRefs;

    public List<String> getSourceRefs() {
        return sourceRefs;
    }

    public List<String> getTargetRefs() {
        return targetRefs;
    }

    public void setSourceRefs(List<String> sourceRefs) {
        this.sourceRefs = sourceRefs;
    }

    public void setTargetRefs(List<String> targetRefs) {
        this.targetRefs = targetRefs;
    }
}
