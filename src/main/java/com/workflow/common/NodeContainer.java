package com.workflow.common;

import java.util.List;

public interface NodeContainer<T extends Node> extends Node {


    List<T> getAllNodes();

    void addNode(T node);

    T getNode(String id);

    T getStartNode();

    T getEndNode();


}
