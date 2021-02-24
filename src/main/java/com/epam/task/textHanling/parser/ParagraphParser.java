package com.epam.task.textHanling.parser;

import java.util.Arrays;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_SEPARATOR = "(([.!?]{1,3})\\s)";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        String[] sentences = input.split(SENTENCE_SEPARATOR);

        Component paragraph = new Composite();

        Arrays.stream(sentences).forEach(sentence -> {
            Component component = getSuccessor().parse(sentence);
            paragraph.add(component);
        });
        return paragraph;
    }

}
