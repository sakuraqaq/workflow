package com.workflow.dynamic;

import com.workflow.dynamic.entity.ClassParam;
import javassist.ClassPool;
import javassist.CtClass;

public interface JavaDynamicCompilation {

    Object buildJavaClass(ClassParam classParam);

    void buildClassFields(ClassPool pool, CtClass aClass, ClassParam classParam);

    void buildClassConstructor(ClassPool pool, CtClass aClass, ClassParam classParam);

    void buildClassMethods(ClassPool pool, CtClass aClass, ClassParam classParam);
}
