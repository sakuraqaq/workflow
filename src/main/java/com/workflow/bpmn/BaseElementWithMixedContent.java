package com.workflow.bpmn;

import com.workflow.common.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseElementWithMixedContent implements Element {

    private List<String> content;

    private String id;

    private Map<String, String> attributes = new HashMap<>();


    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
