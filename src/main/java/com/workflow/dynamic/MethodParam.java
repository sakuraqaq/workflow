package com.workflow.dynamic;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MethodParam implements Serializable {

    /**
     * int,long,double,boolean,object(类全名称),void
     */
    private String voidType;

    private List<Object> params;

    private String mName;

    private Integer modifiers;

    private String body;


}
