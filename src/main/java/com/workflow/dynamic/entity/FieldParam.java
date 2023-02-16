package com.workflow.dynamic.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldParam implements Serializable {

    private String name;

    /**
     * 完整包名
     */
    private String type;

    //   public static final int PUBLIC = 1;
    //    public static final int PRIVATE = 2;
    //    public static final int PROTECTED = 4;
    private Integer modifiers;

    private Object value;

    private Integer length;
}
