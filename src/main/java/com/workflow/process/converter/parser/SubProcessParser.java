package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.SubProcess;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class SubProcessParser extends AbstractBpmnElementParser<SubProcess>{


    @Override
    protected SubProcess doParse(XMLSource xmlSource, ParseContext parseContext) {
        SubProcess subProcess = new SubProcess();
        subProcess.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        subProcess.setName(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_NAME));
        subProcess.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        subProcess.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        subProcess.setParentId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_PARENT_ID));
        subProcess.setClosed(false);
        return subProcess;
    }

    @Override
    protected void addChildElement(Element childElement, SubProcess element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_SUB_PROCESS;
    }
}
