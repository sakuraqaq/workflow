package com.workflow.controller;

import com.workflow.bpmn.BpmnModel;
import com.workflow.process.ProcessEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkflowTestController {


    private final ProcessEngine<BpmnModel> processEngine;

    @GetMapping()
    public String test() {
        processEngine.start("ktvExample", new HashMap<>());
        return "";
    }

    @GetMapping("/doAction")
    public String doAction(Long workflowId){
        Map<String, Object> map = new HashMap<>();
        map.put("payMoney", 1);
        processEngine.doAction(workflowId, map);
        return "";
    }
}
