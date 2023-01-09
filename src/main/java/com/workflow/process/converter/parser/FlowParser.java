package com.workflow.process.converter.parser;

import com.workflow.common.FlowSource;

public interface FlowParser<S extends FlowSource, R> extends Parser{

    R parse(S source);

}
