package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.ExclusiveGateway;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class ExclusiveGatewayParser extends AbstractBpmnElementParser<ExclusiveGateway>{

    @Override
    protected ExclusiveGateway doParse(XMLSource xmlSource, ParseContext parseContext) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();

        return exclusiveGateway;
    }

    @Override
    protected void addChildElement(Element childElement, ExclusiveGateway element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_EXCLUSIVE_GATEWAY;
    }
}
