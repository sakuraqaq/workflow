package com.workflow.common;

import java.util.List;

public interface FlowModel<T extends Node> extends NodeContainer<T> {

    List<T> getRuntimeNodes();

    List<TransitionNode> getTransitionNodes();

    List<IVar> getParamVars();

}
