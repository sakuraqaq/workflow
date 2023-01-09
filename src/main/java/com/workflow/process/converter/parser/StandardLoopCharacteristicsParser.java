package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.StandardLoopCharacteristics;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class StandardLoopCharacteristicsParser extends AbstractBpmnElementParser<StandardLoopCharacteristics> {


    @Override
    protected StandardLoopCharacteristics doParse(XMLSource xmlSource, ParseContext parseContext) {
        StandardLoopCharacteristics standardLoopCharacteristics = new StandardLoopCharacteristics();
        standardLoopCharacteristics.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        standardLoopCharacteristics.setCollection(
                xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_COLLECTION));
        standardLoopCharacteristics.setElementVar(
                xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_ELEMENT_VAR));
        standardLoopCharacteristics.setIndexVar(
                xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_INDEX_VAR));
        standardLoopCharacteristics.setElementVarClass(
                xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_ELEMENT_VAR_CLASS));
        return standardLoopCharacteristics;
    }

    @Override
    protected void addChildElement(Element childElement, StandardLoopCharacteristics element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_STANDARD_LOOP_CHARACTERISTICS;
    }
}
