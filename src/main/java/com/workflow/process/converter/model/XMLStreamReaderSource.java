package com.workflow.process.converter.model;


import org.springframework.util.StringUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLStreamReaderSource implements XMLSource {

    private XMLStreamReader xmlStreamReader;

    public void setXmlStreamReader(XMLStreamReader xmlStreamReader) {
        this.xmlStreamReader = xmlStreamReader;
    }

    public static XMLStreamReaderSource of(XMLStreamReader xmlStreamReader) {
        XMLStreamReaderSource xmlStreamReaderSource = new XMLStreamReaderSource();
        xmlStreamReaderSource.setXmlStreamReader(xmlStreamReader);
        return xmlStreamReaderSource;
    }


    @Override
    public boolean hasNext() throws Exception {
        return xmlStreamReader.hasNext() && XMLStreamReader.END_ELEMENT != xmlStreamReader.next();
    }

    @Override
    public String nextElementName() {
        return xmlStreamReader.isStartElement() ? xmlStreamReader.getLocalName() : null;
    }

    @Override
    public boolean endWith(String name) {
        return xmlStreamReader.isEndElement() && name.equals(xmlStreamReader.getLocalName());
    }

    @Override
    public String getLocalName() {
        return xmlStreamReader.getLocalName();
    }

    @Override
    public String getNamespaceURI() {
        return xmlStreamReader.getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return xmlStreamReader.getPrefix();
    }

    @Override
    public String getElementText() {
        try {
            return xmlStreamReader.getElementText();
        } catch (XMLStreamException e) {
            throw new RuntimeException("找不到节点文本", e);
        }
    }

    @Override
    public String getAttributeLocalName(int index) {
        return xmlStreamReader.getAttributeLocalName(index);
    }

    @Override
    public String getAttributeValue(int index) {
        return xmlStreamReader.getAttributeValue(index);
    }

    @Override
    public String getAttributeNamespace(int index) {
        return xmlStreamReader.getAttributeNamespace(index);
    }

    @Override
    public String getAttributePrefix(int index) {
        return xmlStreamReader.getAttributePrefix(index);
    }

    @Override
    public int getAttributeCount() {
        return xmlStreamReader.getAttributeCount();
    }

    public String getString(String namespaceURI, String name) {
        return xmlStreamReader.getAttributeValue(namespaceURI, name);
    }

    @Override
    public String getString(String name) {
        return getString(null, name);
    }

    @Override
    public String getCfString(String name) {
        return getString("", name);
    }

    private int getInt(String namespaceURI, String name) {
        String vaule = getString(namespaceURI, name);
        return !StringUtils.isEmpty(vaule) ? Integer.valueOf(vaule) : 0;
    }

    private long getLong(String namespaceURI, String name) {
        String vaule = getString(namespaceURI, name);
        return vaule != null ? Long.valueOf(vaule) : 0;
    }

    private boolean getBoolean(String namespaceURI, String name) {
        String vaule = getString(namespaceURI, name);
        return vaule != null ? Boolean.valueOf(vaule) : false;
    }

    @Override
    public int getInt(String name) {
        return getInt(null, name);
    }

    @Override
    public int getCfInt(String name) {
        return 0;
    }

    @Override
    public long getLong(String name) {
        return getLong(null, name);
    }

    @Override
    public long getCfLong(String name) {
        return 0;
    }

    @Override
    public boolean getBoolean(String name) {
        return getBoolean(null, name);
    }

    @Override
    public boolean getCfBoolean(String name) {
        return false;
    }
}
