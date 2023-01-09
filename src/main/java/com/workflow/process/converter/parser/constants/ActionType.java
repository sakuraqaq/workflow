package com.workflow.process.converter.parser.constants;

public enum ActionType {

    SPRING_BEAN("spring-bean"),
    JAVA("java");

    private String type;

    ActionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
