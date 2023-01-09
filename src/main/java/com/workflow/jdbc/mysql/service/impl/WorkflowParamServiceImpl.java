package com.workflow.jdbc.mysql.service.impl;

import com.alibaba.fastjson.JSON;
import com.workflow.jdbc.mysql.entity.WorkflowParam;
import com.workflow.jdbc.mysql.mapper.WorkflowParamMapper;
import com.workflow.jdbc.mysql.service.WorkflowParamService;
import com.workflow.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WorkflowParamServiceImpl implements WorkflowParamService {

    private final WorkflowParamMapper workflowParamMapper;
    private final IdGenerator idGenerator = new IdGenerator(1, 2);

    @Override
    public Serializable createWorkflowParam(Map<String, Object> params, Long workflowId, Long workflowTaskId) {
        WorkflowParam workflowParam = new WorkflowParam();
        workflowParam.setWorkflowParamId(idGenerator.nextId());
        workflowParam.setParams(JSON.toJSONString(params));
        workflowParam.setWorkflowId(workflowId);
        workflowParam.setWorkflowTaskId(workflowTaskId);
        workflowParam.setCreateDate(LocalDateTime.now());
        workflowParamMapper.insert(workflowParam);
        return workflowParam;
    }

    @Override
    public Integer deleteWorkflowParam(Serializable id) {
        return workflowParamMapper.delete(id);
    }

    @Override
    public Integer updateWorkflowParam(WorkflowParam workflowParam) {
        return workflowParamMapper.update(workflowParam);
    }

    @Override
    public WorkflowParam selectByTaskId(Serializable workflowTaskId) {
        return workflowParamMapper.selectByTaskId(workflowTaskId);
    }


}
