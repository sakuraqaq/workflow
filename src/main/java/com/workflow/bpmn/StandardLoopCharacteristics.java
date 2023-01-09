package com.workflow.bpmn;

public class StandardLoopCharacteristics extends LoopCharacteristics{


    private String collection;
    private String elementVarClass;
    private String elementVar;
    private String indexVar;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getElementVarClass() {
        return elementVarClass;
    }

    public void setElementVarClass(String elementVarClass) {
        this.elementVarClass = elementVarClass;
    }

    public String getElementVar() {
        return elementVar;
    }

    public void setElementVar(String elementVar) {
        this.elementVar = elementVar;
    }

    public String getIndexVar() {
        return indexVar;
    }

    public void setIndexVar(String indexVar) {
        this.indexVar = indexVar;
    }
}
