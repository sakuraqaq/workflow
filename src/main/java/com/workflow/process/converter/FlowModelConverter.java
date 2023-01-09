package com.workflow.process.converter;


import com.workflow.common.FlowStreamSource;

public interface FlowModelConverter<T> {

    T convertToModel(FlowStreamSource flowStreamSource);

}
