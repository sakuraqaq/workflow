package com.workflow.common;

public interface Element {

    default String getId(){
        return "UNDEFINED";
    }
}
