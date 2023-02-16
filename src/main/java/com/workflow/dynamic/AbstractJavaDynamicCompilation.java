package com.workflow.dynamic;

import com.workflow.dynamic.entity.ClassParam;
import com.workflow.dynamic.entity.FieldParam;
import com.workflow.dynamic.entity.MethodParam;
import javassist.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class AbstractJavaDynamicCompilation implements JavaDynamicCompilation {


    /**
     * 模版设计模式，子类不要继承
     */
    @Override
    public Object buildJavaClass(ClassParam classParam) {

        ClassPool pool = ClassPool.getDefault();

        //创建一个空的类
        CtClass aClass = pool.makeClass(classParam.getClassName());

        //添加字段
        buildClassFields(pool, aClass, classParam);

        // 添加无参的构造函数
        buildClassConstructor(pool, aClass, classParam);

        //添加方法
        buildClassMethods(pool, aClass, classParam);

        //类输出路径
        try {
            aClass.writeFile(classParam.getClassPath());
            Class<?> toClass;
            toClass = aClass.toClass();
            return toClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | CannotCompileException e) {
            log.error("class {} 构建异常", classParam.getClassName());
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("class {} IO异常", classParam.getClassName());
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buildClassFields(ClassPool pool, CtClass aClass, ClassParam classParam) {

        if (classParam.getFieldParams() == null || classParam.getFieldParams().isEmpty())
            return;

        try {
            for (FieldParam fieldParam : classParam.getFieldParams()) {

                // 初始值是
                Object value = fieldParam.getValue();

                // 新增一个字段名 ,类型
                CtField name;
                CtField.Initializer constant = null;

                name = new CtField(pool.getCtClass(fieldParam.getType()), fieldParam.getName(), aClass);
                // 访问级别
                name.setModifiers(fieldParam.getModifiers());
                if (value != null) {
                    Class<?> fieldClass = value.getClass();
                    if (String.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant(fieldParam.getValue().toString());
                    } else if (Short.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant((int) fieldParam.getValue());
                    } else if (Integer.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant((int) fieldParam.getValue());
                    } else if (Long.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant((long) fieldParam.getValue());
                    } else if (Character.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant(fieldParam.getValue().toString());
                    } else if (Float.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant((float) fieldParam.getValue());
                    } else if (Double.class.isAssignableFrom(fieldClass)) {
                        constant = CtField.Initializer.constant((double) fieldParam.getValue());
                    } else if (fieldClass.isArray()) {
                        constant = CtField.Initializer.byNewArray(pool.getCtClass(fieldClass.getName()), fieldParam.getLength());
                    } else {
                        constant = CtField.Initializer.byNew(pool.getCtClass(fieldClass.getName()));
                    }
                }
                aClass.addField(name, constant);
            }
        } catch (CannotCompileException | NotFoundException e) {
            log.error("新增字段异常", e);
            throw new RuntimeException(e);
        }
        log.info("class {} 新增字段完毕", classParam.getClassName());
    }

    @Override
    public void buildClassConstructor(ClassPool pool, CtClass aClass, ClassParam classParam) {

        CtClass[] ctClasses = {};
        List<String> constructorTypes = classParam.getConstructorTypes();
        if (constructorTypes != null && !constructorTypes.isEmpty()) {
            ctClasses = constructorTypes.stream().map(cp -> {
                try {
                    return pool.get(cp);
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).toArray(CtClass[]::new);
        }

        CtConstructor cons = new CtConstructor(ctClasses, aClass);
        try {
            cons.setBody(classParam.getConstructorBody());//如果没有body会报错
            aClass.addConstructor(cons);
        } catch (CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buildClassMethods(ClassPool pool, CtClass aClass, ClassParam classParam) {

        try {
            log.info("class {} 开始构建方法", classParam.getClassName());
            for (MethodParam methodParam : classParam.getMethodParams()) {
                String voidType = methodParam.getVoidType();
                CtClass voidTypeClass;
                if (!StringUtils.isEmpty(voidType)) {
                    switch (voidType) {
                        case "int":
                            voidTypeClass = CtClass.intType;
                            break;
                        case "long":
                            voidTypeClass = CtClass.longType;
                            break;
                        case "double":
                            voidTypeClass = CtClass.doubleType;
                            break;
                        case "boolean":
                            voidTypeClass = CtClass.booleanType;
                            break;
                        case "void":
                            voidTypeClass = CtClass.voidType;
                            break;
                        default:
                            voidTypeClass = pool.getCtClass(voidType);
                    }
                } else {
                    voidTypeClass = CtClass.voidType;
                }
                List<Object> params = methodParam.getParams();
                CtClass[] ctClasses = {};
                if (params != null && !params.isEmpty()) {
                    ctClasses = params.stream().map(param -> {
                        try {
                            return pool.getCtClass(param.getClass().getName());
                        } catch (NotFoundException e) {
                            log.error("class {} 构建方法参数异常", classParam.getClassName());
                            log.error(e.getMessage(), e);
                            throw new RuntimeException(e);
                        }
                    }).toArray(CtClass[]::new);
                }
                CtMethod ctMethod = new CtMethod(voidTypeClass, methodParam.getMName(), ctClasses, aClass);
                ctMethod.setModifiers(methodParam.getModifiers());

                ctMethod.setBody(methodParam.getBody());
                aClass.addMethod(ctMethod);
            }
            log.info("class {} 构建方法完毕", classParam.getClassName());
        } catch (CannotCompileException | NotFoundException e) {
            log.error("class {} 构建方法异常", classParam.getClassName());
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
