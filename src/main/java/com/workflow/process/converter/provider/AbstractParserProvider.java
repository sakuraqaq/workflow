package com.workflow.process.converter.provider;

import com.workflow.process.converter.parser.Parser;

import java.util.*;

public abstract class AbstractParserProvider<T extends Parser> implements ParserProvider<T> {

    private List<Parser> parsers = new ArrayList<>();

    private Map<String, Parser> parserMap = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public T getParser(String name) {
        return Optional.ofNullable((T) parserMap.get(name)).orElseThrow(
                () -> new RuntimeException("No parser found, name is " + name));
    }

    @Override
    public void registerParser(Parser parser) {
        Parser existedParser = parserMap.get(parser.getName());
        if (existedParser != null && !existedParser.getClass().equals(parser.getClass())) {
            throw new RuntimeException(
                    "Duplicated parser name[" + parser.getName() + "] found, "
                            + "[" + parser.getClass().getName() + ", " + existedParser.getClass().getName() + "]");
        }
        parsers.add(parser);
        parserMap.put(parser.getName(), parser);
    }
}