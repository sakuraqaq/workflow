package com.workflow.common;

import java.io.Serializable;
import java.util.List;

public interface ElementContainer<E extends Element, T extends Node> extends NodeContainer<T>{

    List<E> getElementAll();

    void addElement(E element);

    E getElement(String id);

    String getNextTargetRef();

    String getNodeId();

    void setNodeId(String nodeId);

    Boolean isClosed();

    void setNextTargetRef(String nextTargetRef);

    Serializable getWorkflowId();

    void setWorkflowId(Serializable workflowId);

}
