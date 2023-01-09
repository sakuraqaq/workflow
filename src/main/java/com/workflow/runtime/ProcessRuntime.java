package com.workflow.runtime;

import com.workflow.common.Element;
import com.workflow.common.ElementContainer;
import com.workflow.common.Node;

import java.io.Serializable;
import java.util.Map;

public interface ProcessRuntime<E extends Element, T extends Node> {

    Boolean execute(ElementContainer<E, T> process, String method, Map<String, Object> context) throws Exception;

    void executeNext(ElementContainer<E, T> process, Map<String, Object> context);

    void start(Serializable workflowId, Map<String, Object> context) throws Exception;

    void start(ElementContainer<E, T> process, Map<String, Object> context) throws Exception;
}
