package com.workflow.jdbc.mysql.service;

import com.workflow.jdbc.mysql.entity.WorkflowParam;

import java.io.Serializable;
import java.util.Map;

public interface WorkflowParamService {


    Serializable createWorkflowParam(Map<String, Object> params, Long workflowId, Long workflowTaskId);

    Integer deleteWorkflowParam(Serializable id);

    Integer updateWorkflowParam(WorkflowParam workflowParam);

    WorkflowParam selectByTaskId(Serializable workflowTaskId);
}
