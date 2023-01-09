package com.workflow.process;

import com.workflow.common.FlowModel;

import java.io.Serializable;
import java.util.Map;

public interface ProcessEngine<T extends FlowModel> {

    void reload(String code);

    T load(String code);

    void start(String code, Map<String, Object> params);

    void doAction(Serializable workflowId, Map<String, Object> params);
}
