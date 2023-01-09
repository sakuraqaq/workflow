package com.workflow.bpmn;

import java.util.ArrayList;
import java.util.List;

public class ParallelGateway extends GatewayNode {

    private final List<String> finishNode = new ArrayList<>();

    public List<String> getFinishNode() {
        return finishNode;
    }

    public void addFinishNode(String id) {
        finishNode.add(id);
    }
}