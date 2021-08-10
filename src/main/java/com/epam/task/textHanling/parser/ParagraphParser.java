package com.epam.task.textHanling.parser;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_SEPARATOR = "(([.!?]{1,3})\\s)";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    protected String getSeparator() {
        return SENTENCE_SEPARATOR;
    }

}
