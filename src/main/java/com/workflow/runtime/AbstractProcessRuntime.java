package com.workflow.runtime;

import com.workflow.common.ElementContainer;
import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractProcessRuntime implements ProcessRuntime<FlowElement, FlowNode> {


    @Override
    public void start(ElementContainer<FlowElement, FlowNode> process, Map<String, Object> context) throws Exception {
        execute(process, "start", context);
    }

    @Override
    public Boolean execute(ElementContainer<FlowElement, FlowNode> process, String method, Map<String, Object> context) throws Exception {
        return executeProcessInstance(process, method, context);
    }

    protected Boolean executeProcessInstance(ElementContainer<FlowElement, FlowNode> process, String methodName, Map<String, Object> context) {
        //通过Process 流程模型的Name，在spring中获取Bean
        //通过反射执行bean的method 返回true/false 判断是否继续执行下一个节点
        //after 执行节点入库，Process更新操作
        //executeNode（）执行下一个节点任务
        //BaseProcessRuntime 完成大部分工作
        //runtime.impl包下的类，提供引擎默认的节点执行业务代码
        //整体流程就是 每个流程节点执行完 获取下一个流程节点 然后进行一个递归调用execute()
        Boolean execute;
        try {
            ProcessInstance instance = getProcessInstance(process);
            Method method = instance.getClass().getMethod(methodName, Map.class);
            execute = (Boolean) method.invoke(instance, context);
            if (execute) {
                after(process, context);
                if (!process.isClosed()) {
                    executeNext(process, context);
                }
            }
            return execute;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("method: " + methodName + "() Not Found.", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("method: " + methodName + "() Exception", e);
        }
    }

    protected abstract Serializable initialize(String code, Map<String, Object> context);

    protected abstract void executeNode(ElementContainer<FlowElement, FlowNode> process, FlowNode flowNode, Map<String, Object> context);

    protected abstract ProcessInstance getProcessInstance(ElementContainer<FlowElement, FlowNode> process);

    protected abstract void after(ElementContainer<FlowElement, FlowNode> process, Map<String, Object> context);
}
