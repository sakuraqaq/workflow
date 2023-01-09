package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.UserTask;
import com.workflow.common.Element;
import com.workflow.common.impl.Action;
import com.workflow.common.impl.JavaActionHandle;
import com.workflow.common.impl.SpringBeanActionHandle;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;
import com.workflow.process.converter.parser.constants.ActionType;

public class UserTaskParser extends AbstractBpmnElementParser<UserTask> {

    @Override
    protected UserTask doParse(XMLSource xmlSource, ParseContext parseContext) {
        UserTask userTask = new UserTask();
        userTask.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        userTask.setImplementation(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_IMPLEMENTATION));
        userTask.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        userTask.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        userTask.setTag(getName());
        String type = xmlSource.getCfString(BpmnModelConstants.BPMN_ATTRIBUTE_TYPE);
        userTask.setType(type);

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

        userTask.setAction(action);
        return userTask;
    }

    @Override
    protected void addChildElement(Element childElement, UserTask element, ParseContext parseContext) {
    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_USER_TASK;
    }
}
