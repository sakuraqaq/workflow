package com.workflow.common;

import java.util.List;

public interface TransitionNode extends Node {

    <T extends TransitionNode> List<T> getIncomingNodes();

    <T extends TransitionNode> List<T> getOutgoingNodes();
}
