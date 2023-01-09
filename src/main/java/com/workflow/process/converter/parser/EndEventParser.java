package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.EndEvent;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class EndEventParser extends AbstractBpmnElementParser<EndEvent> {


    @Override
    protected EndEvent doParse(XMLSource xmlSource, ParseContext parseContext) {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        endEvent.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        endEvent.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        return endEvent;
    }

    @Override
    protected void addChildElement(Element childElement, EndEvent element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_END_EVENT;
    }


}
