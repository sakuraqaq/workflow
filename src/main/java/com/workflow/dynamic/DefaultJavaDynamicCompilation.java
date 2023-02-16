package com.workflow.dynamic;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public class DefaultJavaDynamicCompilation extends AbstractJavaDynamicCompilation {

    public static void main(String[] args) {

        ClassParam classParam = new ClassParam();
        classParam.setClassName("TestClass");
        classParam.setClassPath("/Users/biyinghao/Downloads/work/workflow/src/main/resources/");
        classParam.setConstructorBody("{}");
        FieldParam fieldParam = new FieldParam();
        fieldParam.setName("field01");
        fieldParam.setModifiers(1);
        fieldParam.setType(Integer.class.getName());
        classParam.setFieldParams(Collections.singletonList(fieldParam));
        MethodParam methodParam = new MethodParam();
        methodParam.setMName("test");
        methodParam.setModifiers(1);
        methodParam.setVoidType("void");
        methodParam.setBody("{\n" +
                "        System.out.println(\"test 成功\");\n" +
                "    }");
        classParam.setMethodParams(Collections.singletonList(methodParam));
        Object o = new DefaultJavaDynamicCompilation().buildJavaClass(classParam);
        try {
            o.getClass().getMethod("test", new Class[]{}).invoke(o);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


}
