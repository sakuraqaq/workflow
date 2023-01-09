package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.Process;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class ProcessParser extends AbstractBpmnElementParser<Process> {

    @Override
    protected Process doParse(XMLSource xmlSource, ParseContext parseContext) {
        Process process = new Process();
        process.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        process.setName(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_NAME));
        process.setExecutable(xmlSource.getBoolean(BpmnModelConstants.BPMN_ATTRIBUTE_IS_EXECUTABLE));
        process.setClosed(false);
        return process;
    }

    @Override
    protected void addChildElement(Element childElement, Process element, ParseContext parseContext) {
        if (childElement instanceof FlowElement) {
            element.addElement((FlowElement) childElement);
        }
    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_PROCESS;
    }

}
