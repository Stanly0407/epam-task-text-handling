package com.epam.task.textHanling.parser;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class TextParser extends AbstractParser {

    private static final Logger LOGGER = Logger.getLogger(TextParser.class);

    private static final String PARAGRAPH_SEPARATOR = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        String[] paragraphs = input.split(PARAGRAPH_SEPARATOR);

        Component text = new Composite();

        Arrays.stream(paragraphs).forEach(paragraph -> {
            Component component = getSuccessor().parse(paragraph);
            text.add(component);
            LOGGER.debug("TextParser: - " + component + "\n");
        });
        return text;
    }
}
