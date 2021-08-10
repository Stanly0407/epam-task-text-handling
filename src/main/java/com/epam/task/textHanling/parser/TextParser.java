package com.epam.task.textHanling.parser;

public class TextParser extends AbstractParser {

    private static final String PARAGRAPH_SEPARATOR = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    protected String getSeparator() {
        return PARAGRAPH_SEPARATOR;
    }

}
