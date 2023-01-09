package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.ServiceTask;
import com.workflow.common.Element;
import com.workflow.common.impl.Action;
import com.workflow.common.impl.JavaActionHandle;
import com.workflow.common.impl.SpringBeanActionHandle;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;
import com.workflow.process.converter.parser.constants.ActionType;

public class ServiceTaskParser extends AbstractBpmnElementParser<ServiceTask> {


    @Override
    protected ServiceTask doParse(XMLSource xmlSource, ParseContext parseContext) {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        serviceTask.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        serviceTask.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        String type = xmlSource.getCfString(BpmnModelConstants.BPMN_ATTRIBUTE_TYPE);
        serviceTask.setType(type);

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

        serviceTask.setAction(action);
        return serviceTask;
    }

    @Override
    protected void addChildElement(Element childElement, ServiceTask element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_SERVICE_TASK;
    }
}
