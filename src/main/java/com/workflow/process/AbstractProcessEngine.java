package com.workflow.process;

import com.workflow.bpmn.FileFlowStreamSource;
import com.workflow.common.FlowModel;
import com.workflow.common.FlowStreamSource;
import com.workflow.common.TransitionNode;
import com.workflow.process.converter.FlowModelConverter;

import java.io.File;

public abstract class AbstractProcessEngine<T extends FlowModel<? extends TransitionNode>> implements ProcessEngine<T> {


    @Override
    public void reload(String code) {

    }

    @Override
    public T load(String code) {
        FlowStreamSource flowStreamSource = loadFlowSource(code);

        T flowModel = (T) getFlowModelConverter().convertToModel(flowStreamSource);
        if (flowModel == null) {
            throw new RuntimeException("No valid flow model found, code is " + code);
        }

        return flowModel;
    }

    private FlowStreamSource loadFlowSource(String code) {
        String filePath = convertToFilePath(code);
        return FileFlowStreamSource.of(new File(filePath));
    }

    private String convertToFilePath(String code) {
        String path = code.replace(".", "/");
        return path + ".bpmn20";
    }

    protected abstract FlowModelConverter<T> getFlowModelConverter();

}
