package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.ParallelGateway;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

import java.util.Arrays;

public class ParallelGatewayParser extends AbstractBpmnElementParser<ParallelGateway> {

    @Override
    protected ParallelGateway doParse(XMLSource xmlSource, ParseContext parseContext) {
        ParallelGateway parallelGateway = new ParallelGateway();
        parallelGateway.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        parallelGateway.setName(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_NAME));
        parallelGateway.setSourceRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF));
        parallelGateway.setTargetRef(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF));
        String sourceRefs = xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REFS);
        String targetRefs = xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REFS);
        if (sourceRefs != null && !"".equals(sourceRefs)) {
            parallelGateway.setSourceRefs(Arrays.asList(sourceRefs.split(",")));
        }
        if (targetRefs != null && !"".equals(targetRefs)) {
            parallelGateway.setTargetRefs(Arrays.asList(targetRefs.split(",")));
        }
        return parallelGateway;
    }

    @Override
    protected void addChildElement(Element childElement, ParallelGateway element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_PARALLEL_GATEWAY;
    }
}
