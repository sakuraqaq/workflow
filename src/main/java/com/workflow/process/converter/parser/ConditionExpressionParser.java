package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.Expression;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class ConditionExpressionParser extends AbstractBpmnElementParser<Expression>{


    @Override
    protected Expression doParse(XMLSource xmlSource, ParseContext parseContext) {
        Expression expression = new Expression();
        String expressionValue = xmlSource.getElementText();
        if (expressionValue != null) {
            expression.setValue(expressionValue.trim());
        }
        return expression;
    }

    @Override
    protected void parseChildElements(XMLSource xmlSource, Expression element, ParseContext parseContext) throws Exception {

    }

    @Override
    protected void addChildElement(Element childElement, Expression element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_CONDITION_EXPRESSION;
    }
}
