package com.workflow.process.converter;

import com.workflow.bpmn.BpmnModel;
import com.workflow.common.FlowStreamSource;
import com.workflow.process.converter.parser.BpmnStreamParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BpmnModelConverter implements FlowModelConverter<BpmnModel> {

    private static final Map<String, BpmnModel> modelCache = new ConcurrentHashMap<>();

    private BpmnModelConverter(){
    }

    public static Map<String, BpmnModel> getModelCache() {
        return modelCache;
    }

    private static final BpmnModelConverter bpmnModelConverter = new BpmnModelConverter();

    public static BpmnModelConverter getInstance(){
        return bpmnModelConverter;
    }

    @Override
    public BpmnModel convertToModel(FlowStreamSource flowStreamSource) {
        BpmnModel parse = BpmnStreamParser.getInstance().parse(flowStreamSource);
        modelCache.put(parse.getProcess().getId(), parse);
        return parse;
    }
}
