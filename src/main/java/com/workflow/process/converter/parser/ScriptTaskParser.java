package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.Script;
import com.workflow.bpmn.ScriptTask;
import com.workflow.common.Element;
import com.workflow.common.impl.Action;
import com.workflow.common.impl.JavaActionHandle;
import com.workflow.common.impl.SpringBeanActionHandle;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;
import com.workflow.process.converter.parser.constants.ActionType;

public class ScriptTaskParser extends AbstractBpmnElementParser<ScriptTask>{


    @Override
    protected ScriptTask doParse(XMLSource xmlSource, ParseContext parseContext) {
        ScriptTask scriptTask = new ScriptTask();
        scriptTask.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        scriptTask.setScriptFormat(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SCRIPT_FORMAT));
        scriptTask.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        scriptTask.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        String type = xmlSource.getCfString(BpmnModelConstants.BPMN_ATTRIBUTE_TYPE);
        scriptTask.setType(type);

        Action action = new Action();
        action.setType(type);
        if (ActionType.SPRING_BEAN.getType().equals(type)) {
            SpringBeanActionHandle actionHandle = new SpringBeanActionHandle();
            actionHandle.setBean(xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_BEAN));
            actionHandle.setClazz(xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_CLASS));
            actionHandle.setMethod(xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_METHOD));
            action.setActionHandle(actionHandle);
        } else if (ActionType.JAVA.getType().equals(type)) {
            JavaActionHandle actionHandle = new JavaActionHandle();
            actionHandle.setClazz(xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_CLASS));
            actionHandle.setMethod(xmlSource.getCfString(BpmnModelConstants.BPMN_EXT_ATTRIBUTE_METHOD));
            action.setActionHandle(actionHandle);
        }

        scriptTask.setAction(action);
        return scriptTask;
    }

    @Override
    protected void addChildElement(Element childElement, ScriptTask element, ParseContext parseContext) {
        if(childElement instanceof Script){
            element.setScript(((Script) childElement).getContent());
        }
    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_SCRIPT_TASK;
    }
}
