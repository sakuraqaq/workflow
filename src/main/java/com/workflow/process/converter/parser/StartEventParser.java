package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.StartEvent;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class StartEventParser extends AbstractBpmnElementParser<StartEvent> {

    @Override
    protected StartEvent doParse(XMLSource xmlSource, ParseContext parseContext) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        startEvent.setName(BpmnModelConstants.BPMN_ELEMENT_START_EVENT);
        startEvent.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        startEvent.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        return startEvent;
    }

    @Override
    protected void addChildElement(Element childElement, StartEvent element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_START_EVENT;
    }

}
