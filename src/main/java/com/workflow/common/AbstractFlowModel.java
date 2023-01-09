package com.workflow.common;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFlowModel<T extends Node> implements FlowModel<T> {

    private String id;

    private String name;

    private List<IVar> vars = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTag() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IVar> getVars() {
        return vars;
    }

    public void setVars(List<IVar> vars) {
        this.vars = vars;
    }

    @Override
    public List<IVar> getParamVars() {
        return vars;
    }
}
