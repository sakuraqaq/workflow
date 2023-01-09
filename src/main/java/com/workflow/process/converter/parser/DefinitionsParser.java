package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.Definitions;
import com.workflow.bpmn.Process;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class DefinitionsParser extends AbstractBpmnElementParser<Definitions> {


    @Override
    protected Definitions doParse(XMLSource xmlSource, ParseContext parseContext) {
        Definitions definitions = new Definitions();
        definitions.setId(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_ID));
        definitions.setTargetNamespace(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_NAMESPACE));
        definitions.setName(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_NAME));
        definitions.setExpressionLanguage(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_EXPRESSION_LANGUAGE));
        definitions.setTypeLanguage(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_TYPE_LANGUAGE));
        definitions.setExporter(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_EXPORTER));
        definitions.setExporterVersion(xmlSource.getString(BpmnModelConstants.BPMN_ATTRIBUTE_EXPORTER_VERSION));
        parseContext.setTop(definitions);
        return definitions;
    }

    @Override
    protected void addChildElement(Element childElement, Definitions element, ParseContext parseContext) {
        if (childElement instanceof Process) {
            element.getProcesses().add((Process) childElement);
        }
    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_DEFINITIONS;
    }

}
