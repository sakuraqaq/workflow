package com.workflow.jdbc.mysql.service;

import com.workflow.bpmn.FlowNode;
import com.workflow.jdbc.mysql.entity.WorkflowTask;

import java.io.Serializable;
import java.util.List;

public interface WorkflowTaskService {

    WorkflowTask createWorkflowTask(FlowNode flowNode, Long workflowId);
    Integer deleteWorkflowTask(Serializable id);
    Integer updateWorkflowTask(WorkflowTask workflowTask);
    List<WorkflowTask> select(Long workflowId);
}
