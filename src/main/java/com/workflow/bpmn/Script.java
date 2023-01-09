package com.workflow.bpmn;

import com.workflow.common.Element;

public class Script implements Element {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
