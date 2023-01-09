package com.workflow.jdbc.mysql.service;

import com.workflow.common.Element;
import com.workflow.common.ElementContainer;
import com.workflow.common.Node;
import com.workflow.jdbc.mysql.entity.Workflow;

import java.io.Serializable;

public interface WorkflowService<E extends Element, T extends Node> {

    Serializable createWorkflow(ElementContainer<E, T> process, Long parentId);

    Integer deleteWorkflow(Serializable id);
    Integer updateWorkflow(Workflow workflow);

    Workflow selectById(Serializable id);

}
