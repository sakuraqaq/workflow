package com.workflow.service;


import com.workflow.runtime.AbstractProcessInstance;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("test")
public class TestProcessService extends AbstractProcessInstance {

    public boolean sing(Map<String, Object> context) {
        System.out.println("sing()");
        return true;
    }

    public Boolean originalPrice(Map<String, Object> context) {
        System.out.println("originalPrice()");
        return true;
    }

    public Boolean promotionPrice(Map<String, Object> context) {
        System.out.println("promotionPrice()");
        return true;
    }

    public Boolean payMoney(Map<String, Object> context) {
        System.out.println("payMoney()");
        if (context.get("payMoney") != null) {
            return true;
        } else
            return false;
    }

}
