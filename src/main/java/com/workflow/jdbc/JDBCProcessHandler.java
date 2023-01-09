package com.workflow.jdbc;

import com.workflow.bpmn.FlowNode;
import com.workflow.common.Element;
import com.workflow.common.ElementContainer;
import com.workflow.common.Node;
import com.workflow.jdbc.mysql.entity.Workflow;
import com.workflow.jdbc.mysql.entity.WorkflowTask;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface JDBCProcessHandler<E extends Element, T extends Node> {

    Serializable saveProcess(ElementContainer<E, T> process);

    void saveNode(FlowNode flowNode, Serializable workflowId, Map<String, Object> context);

    Workflow selectById(Serializable id);

    List<WorkflowTask> listWorkflowTask(Serializable workflowId);


}
