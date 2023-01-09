package com.workflow.jdbc.mysql.service.impl;

import com.workflow.bpmn.FlowNode;
import com.workflow.jdbc.mysql.entity.WorkflowTask;
import com.workflow.jdbc.mysql.mapper.WorkflowTaskMapper;
import com.workflow.jdbc.mysql.service.WorkflowTaskService;
import com.workflow.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowTaskServiceImpl implements WorkflowTaskService {

    private final WorkflowTaskMapper workflowTaskMapper;
    private final IdGenerator idGenerator = new IdGenerator(1, 2);

    @Override
    public WorkflowTask createWorkflowTask(FlowNode flowNode, Long workflowId) {
        WorkflowTask workflowTask = new WorkflowTask();
        workflowTask.setWorkflowTaskId(idGenerator.nextId());
        workflowTask.setCreateDate(LocalDateTime.now());
        workflowTask.setCode(flowNode.getId());
        workflowTask.setNodeType(flowNode.getTag());
        workflowTask.setWorkflowId(workflowId);
        workflowTask.setState(1);
        workflowTaskMapper.insert(workflowTask);
        return workflowTask;
    }

    @Override
    public Integer deleteWorkflowTask(Serializable id) {
        return workflowTaskMapper.delete(id);
    }

    @Override
    public Integer updateWorkflowTask(WorkflowTask workflowTask) {
        return workflowTaskMapper.update(workflowTask);
    }

    @Override
    public List<WorkflowTask> select(Long workflowId) {
        return workflowTaskMapper.select(workflowId);
    }
}
