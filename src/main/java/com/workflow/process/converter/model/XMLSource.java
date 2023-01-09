package com.workflow.process.converter.model;

public interface XMLSource {

    boolean hasNext() throws Exception;

    String nextElementName() throws Exception;

    boolean endWith(String name);

    String getLocalName();

    String getNamespaceURI();

    String getPrefix();

    String getElementText();

    String getAttributeLocalName(int index);

    String getAttributeValue(int index);

    String getAttributeNamespace(int index);

    String getAttributePrefix(int index);

    int getAttributeCount();

    String getString(String name);

    String getCfString(String name);

    int getInt(String name);

    int getCfInt(String name);

    long getLong(String name);

    long getCfLong(String name);

    boolean getBoolean(String name);

    boolean getCfBoolean(String name);
}
