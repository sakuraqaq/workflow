package com.workflow.bpmn;

import java.util.List;

public class UserTask extends Task {

    private List<Rendering> renderings;

    private String implementation;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Rendering> getRenderings() {
        return renderings;
    }

    public void setRenderings(List<Rendering> renderings) {
        this.renderings = renderings;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }
}
