package com.workflow.dynamic;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassParam implements Serializable {

    private String classPath;

    private String className;

    // $0=this / $1,$2,$3... 代表方法参数
    //cons.setBody("{$0.id = $1;$0.name = $2;}");
    private String constructorBody;

    private List<String> constructorTypes;

    private List<FieldParam> fieldParams;

    private List<MethodParam> methodParams;


}
