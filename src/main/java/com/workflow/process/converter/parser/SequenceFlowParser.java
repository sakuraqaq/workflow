package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.Expression;
import com.workflow.bpmn.SequenceFlow;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class SequenceFlowParser extends AbstractBpmnElementParser<SequenceFlow>{

    @Override
    protected SequenceFlow doParse(XMLSource xmlSource, ParseContext parseContext) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        sequenceFlow.setName(getName());
        sequenceFlow.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        sequenceFlow.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        return sequenceFlow;
    }

    @Override
    protected void addChildElement(Element childElement, SequenceFlow element, ParseContext parseContext) {
        // TODO: 2022/10/31 表达式
        if (childElement instanceof Expression) {
            element.setConditionExpression(((Expression) childElement).getValue());
        }
    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_SEQUENCE_FLOW;
    }
}
