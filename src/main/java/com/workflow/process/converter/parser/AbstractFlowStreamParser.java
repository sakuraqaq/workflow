package com.workflow.process.converter.parser;

import com.workflow.common.Element;
import com.workflow.common.FlowStreamSource;
import com.workflow.process.converter.model.ParseContext;
import com.workflow.process.converter.model.XMLSource;
import com.workflow.process.converter.model.XMLStreamReaderSource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public abstract class AbstractFlowStreamParser<R> implements FlowStreamParser<R> {


    @Override
    public R parse(FlowStreamSource source) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(source.getFlow());
            xmlInputFactory.createFilteredReader(xmlStreamReader,
                    reader -> XMLStreamConstants.START_DOCUMENT == reader.getEventType()
                            || XMLStreamConstants.END_DOCUMENT == reader.getEventType()
                            || XMLStreamConstants.START_ELEMENT == reader.getEventType()
                            || XMLStreamConstants.END_ELEMENT == reader.getEventType());
            return parseFlowModel(XMLStreamReaderSource.of(xmlStreamReader));
        } catch (XMLStreamException e) {
            throw new RuntimeException("eee", e);
        } catch (Exception e) {
            throw new RuntimeException("parse 异常", e);
        }
    }

    public R parseFlowModel(XMLSource xmlSource) throws Exception {
        ParseContext parseContext = new ParseContext();
        while (xmlSource.hasNext()) {
            String elementName = xmlSource.nextElementName();
            if (elementName != null) {
                getFlowElementParserProvider().getParser(elementName).parse(xmlSource, parseContext);
            }
        }
        return convertToFlowModel(parseContext.getTop());
    }

    protected abstract AbstractFlowElementParserProvider getFlowElementParserProvider();

    protected abstract R convertToFlowModel(Element top);

}
