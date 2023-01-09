package com.workflow.process.converter.provider;

import com.workflow.process.converter.parser.Parser;

public interface ParserProvider<T extends Parser> {

    T getParser(String name);

    void registerParser(Parser parser);
}
