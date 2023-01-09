package com.workflow.jdbc.mysql;


import com.workflow.bpmn.*;
import com.workflow.bpmn.Process;
import com.workflow.common.ElementContainer;
import com.workflow.jdbc.AbstractJDBCProcessHandler;
import com.workflow.jdbc.mysql.entity.Workflow;
import com.workflow.jdbc.mysql.entity.WorkflowTask;
import com.workflow.jdbc.mysql.service.WorkflowParamService;
import com.workflow.jdbc.mysql.service.WorkflowService;
import com.workflow.jdbc.mysql.service.WorkflowTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component("mybatisProcessHandler")
@RequiredArgsConstructor
public class MybatisProcessHandler extends AbstractJDBCProcessHandler {

    private final WorkflowService<FlowElement, FlowNode> workflowService;
    private final WorkflowTaskService workflowTaskService;
    private final WorkflowParamService workflowParamService;


    @Override
    public Serializable saveProcess(ElementContainer<FlowElement, FlowNode> process) {
        // TODO: 2022/11/8  事物 回滚机制

        if (process instanceof Process) {
            return workflowService.createWorkflow(process, -1L);
        } else if (process instanceof SubProcess) {
            return workflowService.createWorkflow(process, (long) ((SubProcess) process).getParentWorkflowId());
        }
        return 0;
    }

    @Override
    public void saveNode(FlowNode flowNode, Serializable workflowId, Map<String, Object> context) {

        // TODO: 2022/11/8 事物 回滚机制

        WorkflowTask workflowTask = workflowTaskService.createWorkflowTask(flowNode, (Long) workflowId);

        workflowParamService.createWorkflowParam(context, workflowTask.getWorkflowId(), workflowTask.getWorkflowTaskId());

        Workflow workflow = new Workflow();
        if(flowNode instanceof EndEvent){
            workflow.setIsClosed(1);
        }
        workflow.setWorkflowId(workflowTask.getWorkflowId());
        workflow.setNowNode(workflowTask.getCode());
        workflowService.updateWorkflow(workflow);

    }

    @Override
    public Workflow selectById(Serializable id) {
        return workflowService.selectById(id);
    }

    @Override
    public List<WorkflowTask> listWorkflowTask(Serializable workflowId) {
        return workflowTaskService.select((Long) workflowId);
    }
}
