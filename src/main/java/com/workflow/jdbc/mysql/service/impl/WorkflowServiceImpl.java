package com.workflow.jdbc.mysql.service.impl;

import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;
import com.workflow.common.ElementContainer;
import com.workflow.jdbc.mysql.entity.Workflow;
import com.workflow.jdbc.mysql.mapper.WorkflowMapper;
import com.workflow.jdbc.mysql.service.WorkflowService;
import com.workflow.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;

@Service("workflowService")
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService<FlowElement, FlowNode> {

    private final WorkflowMapper workflowMapper;
    private final IdGenerator idGenerator = new IdGenerator(1, 2);

    @Override
    public Serializable createWorkflow(ElementContainer<FlowElement, FlowNode> process, Long parentId) {
        Workflow workflow = new Workflow();
        workflow.setWorkflowId(idGenerator.nextId());
        workflow.setCode(process.getId());
        workflow.setIsClosed(0);
        workflow.setNowNode(process.getNodeId());
        workflow.setParentId(parentId);
        workflow.setCreateDate(LocalDateTime.now());
        workflowMapper.insert(workflow);
        return workflow.getWorkflowId();
    }

    @Override
    public Integer deleteWorkflow(Serializable id) {
        return workflowMapper.delete(id);
    }

    @Override
    public Integer updateWorkflow(Workflow workflow) {
        return workflowMapper.update(workflow);
    }

    @Override
    public Workflow selectById(Serializable id) {
        return workflowMapper.selectOne(id);
    }


}
