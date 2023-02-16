package com.workflow.runtime.impl;

import com.workflow.bpmn.Process;
import com.workflow.common.ElementContainer;
import com.workflow.jdbc.AbstractJDBCProcessHandler;
import com.workflow.jdbc.JDBCProcessHandler;
import com.workflow.jdbc.mysql.entity.Workflow;
import com.workflow.process.converter.BpmnModelConverter;
import com.workflow.runtime.AbstractProcessRuntime;
import com.workflow.runtime.ProcessInstance;
import com.workflow.bpmn.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BaseProcessRuntime extends AbstractProcessRuntime {

    @Override
    public void after(ElementContainer<FlowElement, FlowNode> process, Map<String, Object> context) {

        List<FlowElement> allNodes = new ArrayList<>();
        FlowElement flowElement;
        if (process instanceof Process) {
            Process p = (Process) process;
            allNodes = p.getFlowElements();
        } else if (process instanceof SubProcess) {
            SubProcess subProcess = (SubProcess) process;
            allNodes = subProcess.getElementAll();
        }
        //上一个完成节点
        flowElement = allNodes.stream().filter(node -> node.getId().equals(process.getNodeId())).collect(Collectors.toList()).get(0);
        //jdbc持久化
        AbstractJDBCProcessHandler.getInstance(AbstractJDBCProcessHandler.ORM_MYBATIS)
                .saveNode((FlowNode) flowElement, process.getWorkflowId(), context);
        //设置下一个节点
        process.setNextTargetRef(flowElement.getTargetRef());
        // TODO: 2022/11/3 end 节点表示这条流程结束
    }

    protected void executeNode(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context) {
        if (flowNode instanceof ServiceTask) {
            new ServiceTaskProcessRuntime().execute(process, flowNode, context);
        } else if (flowNode instanceof ScriptTask) {
            new ScriptTaskProcessRuntime().execute(process, flowNode, context);
        } else if (flowNode instanceof EndEvent) {
            new EndEventProcessRuntime().execute(process, flowNode, context);
        } else if (flowNode instanceof SubProcess) {
            new SubProcessRuntime().execute(process, flowNode, context);
        } else if (flowNode instanceof ParallelGateway) {
            new ParallelGatewayProcessRuntime().execute(process, flowNode, context);
        } else if (flowNode instanceof UserTask) {
            new UserTaskProcessRuntime().execute(process, flowNode, context);
        }
    }

    @Override
    protected ProcessInstance getProcessInstance(ElementContainer<FlowElement, FlowNode> process) {
        // TODO: 2023/2/16 需要区分类是bean 还是一个普通的class 
        // TODO: 2022/11/1 dubbo 获取提供者
        String name = process.getName();
        // TODO: 2022/11/1 找到对应的实现类
        return AbstractJDBCProcessHandler.getBean(name);
    }

    @Override
    public Serializable initialize(String code, Map<String, Object> context) {

        BpmnModel bpmnModel = BpmnModelConverter.getModelCache().get(code);
        Process process = bpmnModel.getProcess();
        FlowNode startNode = bpmnModel.getStartNode();
        process.setNodeId(startNode.getId());
        process.setNextTargetRef(startNode.getTargetRef());

        // jdbc
        Serializable workflowId = AbstractJDBCProcessHandler.getInstance(AbstractJDBCProcessHandler.ORM_MYBATIS)
                .saveProcess(process);
        process.setWorkflowId(workflowId);

        return workflowId;
    }


    @Override
    public void executeNext(ElementContainer<FlowElement, FlowNode> process, Map<String, Object> context) {

        List<FlowNode> nextNodes = new ArrayList<>();
        List<FlowNode> allNodes = process.getAllNodes();
        Map<String, FlowNode> flowNodeMap = allNodes.stream().collect(Collectors.toMap(BaseElement::getId, node -> node));
        FlowNode nextNode = flowNodeMap.get(process.getNextTargetRef());
        nextNodes.add(nextNode);

        if (nextNode instanceof ParallelGateway) {
            new ParallelGatewayProcessRuntime().executeNext(nextNodes, nextNode, flowNodeMap);
        }

        nextNodes.forEach(node -> {
            process.setNodeId(node.getId());
            executeNode(process, node, context);
        });
    }

    @Override
    public void start(Serializable workflowId, Map<String, Object> context) throws Exception {
        JDBCProcessHandler<FlowElement, FlowNode> instance = AbstractJDBCProcessHandler.getInstance(AbstractJDBCProcessHandler.ORM_MYBATIS);
        Workflow workflow = instance.selectById(workflowId);
        String code = workflow.getCode();
        BpmnModel bpmnModel = BpmnModelConverter.getModelCache().get(code);
        FlowNode startNode = bpmnModel.getStartNode();
        Process process = bpmnModel.getProcess();
        process.setNodeId(startNode.getId());
        process.setNextTargetRef(startNode.getTargetRef());
        process.setWorkflowId(workflowId);
        start(process, context);
    }
}
