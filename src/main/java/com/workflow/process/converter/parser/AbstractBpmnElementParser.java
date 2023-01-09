package com.workflow.process.converter.parser;

import com.workflow.bpmn.FlowElement;
import com.workflow.common.Element;
import com.workflow.common.ElementContainer;
import com.workflow.common.IAction;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.provider.BpmnElementParserProvider;

public abstract class AbstractBpmnElementParser<E extends Element> extends AbstractFlowElementParser<E> {

    @Override
    public AbstractFlowElementParserProvider getParserProvider() {
        return BpmnElementParserProvider.getInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean addPlatformChildElement(Element childElement, E element, ParseContext parseContext) {

        if (element instanceof ElementContainer && childElement instanceof FlowElement) {
            ((ElementContainer) element).addElement(childElement);
            return true;
        }

        if (element instanceof HasAction && childElement instanceof IAction) {
            ((HasAction) element).setAction((IAction) childElement);
            return true;
        }
        return false;
    }

}
