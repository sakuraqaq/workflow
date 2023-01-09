package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModel;
import com.workflow.bpmn.Definitions;
import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.Process;
import com.workflow.common.Element;
import com.workflow.process.converter.provider.BpmnElementParserProvider;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class BpmnStreamParser extends AbstractFlowStreamParser<BpmnModel>{

    private BpmnStreamParser(){}

    private static final BpmnStreamParser bpmnStreamParser = new BpmnStreamParser();

    public static BpmnStreamParser getInstance(){
        return bpmnStreamParser;
    }


    @Override
    public String getName() {
        return "bpmn";
    }

    @Override
    protected AbstractFlowElementParserProvider getFlowElementParserProvider() {
        return BpmnElementParserProvider.getInstance();
    }

    @Override
    protected BpmnModel convertToFlowModel(Element top) {
        if(top instanceof Definitions){
            Definitions definitions = (Definitions) top;
            List<Process> processes = definitions.getProcesses();
            if (CollectionUtils.isEmpty(processes)) {
                throw new RuntimeException("No process founded");
            }
            if (processes.size() > 1) {
                throw new RuntimeException("Only one process supported");
            }

            Process process = processes.get(0);
            List<FlowElement> flowElements = process.getFlowElements();
            if (CollectionUtils.isEmpty(flowElements)) {
                throw new RuntimeException("No process element founded");
            }
            return buildBpmnModel(process);
        }
        throw new RuntimeException("No flow definition founded");
    }

    private BpmnModel buildBpmnModel(Process process) {
        String id = process.getId();
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("Process has no id");
        }
        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.setId(id);
        bpmnModel.setName(process.getName());
        bpmnModel.addProcesses(process);
        // TODO: 2022/10/29 添加var
        return bpmnModel;

    }
}
