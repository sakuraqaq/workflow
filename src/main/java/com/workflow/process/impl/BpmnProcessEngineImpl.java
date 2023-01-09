package com.workflow.process.impl;


import com.workflow.bpmn.FileFlowStreamSource;
import com.workflow.bpmn.Process;
import com.workflow.jdbc.AbstractJDBCProcessHandler;
import com.workflow.jdbc.mysql.MybatisProcessHandler;
import com.workflow.jdbc.mysql.entity.Workflow;
import com.workflow.process.AbstractProcessEngine;
import com.workflow.process.ProcessEngine;
import com.workflow.bpmn.BpmnModel;
import com.workflow.process.converter.BpmnModelConverter;
import com.workflow.process.converter.FlowModelConverter;
import com.workflow.runtime.impl.BaseProcessRuntime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BpmnProcessEngineImpl extends AbstractProcessEngine<BpmnModel>  implements ProcessEngine<BpmnModel> {

    private final BaseProcessRuntime baseProcessRuntime;

    @Override
    protected FlowModelConverter<BpmnModel> getFlowModelConverter() {
        return BpmnModelConverter.getInstance();
    }


    @Override
    public void start(String code, Map<String, Object> params) {
        BpmnModelConverter
                .getInstance()
                .convertToModel(FileFlowStreamSource.of(new File("/Users/biyinghao/Downloads/work/WebChat/wc-execute/src/main/resources/ktvExample.bpmn20")));

        Serializable workflowId = baseProcessRuntime.initialize(code, params);
        try {
            baseProcessRuntime.start(workflowId, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doAction(Serializable workflowId, Map<String, Object> params) {
        MybatisProcessHandler instance = (MybatisProcessHandler) AbstractJDBCProcessHandler.getInstance(AbstractJDBCProcessHandler.ORM_MYBATIS);
        Workflow workflow = instance.selectById(workflowId);
        BpmnModel bpmnModel = BpmnModelConverter.getModelCache().get(workflow.getCode());
        Process process = bpmnModel.getProcess();
        String nowNode = workflow.getNowNode();//执行完的节点找下一个节点
        process.setNodeId(nowNode);
        baseProcessRuntime.executeNext(process, params);
    }
}
