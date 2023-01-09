package com.workflow.common;

public interface IAction {

    IActionHandle getActionHandle();

    String getType();
}
