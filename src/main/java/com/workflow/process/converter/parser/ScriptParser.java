package com.workflow.process.converter.parser;

import com.workflow.bpmn.BpmnModelConstants;
import com.workflow.bpmn.Script;
import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;

public class ScriptParser extends AbstractBpmnElementParser<Script>{


    @Override
    protected Script doParse(XMLSource xmlSource, ParseContext parseContext) {
        Script script = new Script();
        String scriptContent = xmlSource.getElementText();
        if (scriptContent != null) {
            script.setContent(scriptContent.trim());
        }
        return script;
    }

    @Override
    protected void parseChildElements(XMLSource xmlSource, Script element, ParseContext parseContext) throws Exception {

    }

    @Override
    protected void addChildElement(Element childElement, Script element, ParseContext parseContext) {

    }

    @Override
    public String getName() {
        return BpmnModelConstants.BPMN_ELEMENT_SCRIPT;
    }
}
