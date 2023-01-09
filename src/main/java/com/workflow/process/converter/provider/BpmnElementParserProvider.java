package com.workflow.process.converter.provider;


import com.workflow.process.converter.parser.*;

public class BpmnElementParserProvider extends AbstractFlowElementParserProvider {

    private static volatile BpmnElementParserProvider bpmnElementParserProvider;

    public static BpmnElementParserProvider getInstance() {
        if (bpmnElementParserProvider == null) {
            synchronized (BpmnElementParserProvider.class) {
                if (bpmnElementParserProvider == null) {
                    bpmnElementParserProvider = new BpmnElementParserProvider();
                    bpmnElementParserProvider.init();
                }
            }
        }
        return bpmnElementParserProvider;
    }

    public void init(){
        registerParser(new DefinitionsParser());
        registerParser(new ProcessParser());
        registerParser(new StartEventParser());
        registerParser(new SequenceFlowParser());
        registerParser(new SubProcessParser());
        registerParser(new StandardLoopCharacteristicsParser());
        registerParser(new ConditionExpressionParser());
        registerParser(new ServiceTaskParser());
        registerParser(new ScriptTaskParser());
        registerParser(new ScriptParser());
        registerParser(new UserTaskParser());
        registerParser(new ExclusiveGatewayParser());
        registerParser(new ParallelGatewayParser());
        registerParser(new EndEventParser());
    }

}
