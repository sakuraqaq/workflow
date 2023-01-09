package com.workflow.jdbc;

import com.workflow.bpmn.FlowElement;
import com.workflow.bpmn.FlowNode;
import com.workflow.jdbc.mysql.MybatisProcessHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractJDBCProcessHandler implements JDBCProcessHandler<FlowElement, FlowNode>, ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static final String ORM_MYBATIS = "Mybatis";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AbstractJDBCProcessHandler.applicationContext = applicationContext;
    }

    public static JDBCProcessHandler<FlowElement, FlowNode> getInstance(String type) {
        if (type.equals(ORM_MYBATIS)) {
            return (MybatisProcessHandler) applicationContext.getBean("mybatisProcessHandler");
        } else if (type.equals("hib")) {
            return (MybatisProcessHandler) applicationContext.getBean("mybatisProcessHandler");
        } else if (type.equals("jdbc")) {
            return (MybatisProcessHandler) applicationContext.getBean("mybatisProcessHandler");
        }
        return (MybatisProcessHandler) applicationContext.getBean("mybatisProcessHandler");
    }

    public static  <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

}
