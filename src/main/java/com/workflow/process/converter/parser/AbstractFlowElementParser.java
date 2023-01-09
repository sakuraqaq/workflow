package com.workflow.process.converter.parser;

import com.workflow.common.Element;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;
import com.workflow.process.converter.provider.ParserProviderSupport;

public abstract class AbstractFlowElementParser<E extends Element> implements FlowElementParser<E>,
        ParserProviderSupport<AbstractFlowElementParserProvider> {

    @Override
    public E parse(XMLSource xmlSource, ParseContext parseContext) throws Exception {
        E element = doParse(xmlSource, parseContext);
        parseContext.setParent(element);
        parseChildElements(xmlSource, element, parseContext);
        return element;
    }

    protected void parseChildElements(XMLSource xmlSource, E element, ParseContext parseContext) throws Exception {
        while (xmlSource.hasNext()) {
            String elementName = xmlSource.nextElementName();
            if (elementName != null) {
                Element childElement = getParserProvider().getParser(elementName)
                        .parse(xmlSource, parseContext);
                if (!addPlatformChildElement(childElement, element, parseContext)) {
                    addChildElement(childElement, element, parseContext);
                }
            }
        }
    }

    protected abstract E doParse(XMLSource xmlSource, ParseContext parseContext);

    protected abstract boolean addPlatformChildElement(Element childElement, E element, ParseContext parseContext);

    protected abstract void addChildElement(Element childElement, E element, ParseContext parseContext);

}
