package com.epam.task.textHanling.parser;


import java.util.Arrays;

public class SentenceParser extends AbstractParser {

    private static final String LEXEME_SEPARATOR = "[,;]?\\s";

    public SentenceParser() {
    }

    @Override
    public Component parse(String input) {
        String[] lexemes = input.split(LEXEME_SEPARATOR);

        Component sentence = new Composite();

        Arrays.stream(lexemes).forEach(lexeme -> {
            Component lexemeElement = new Lexeme(lexeme);
            lexemeElement.operation();
            sentence.add(lexemeElement);
        });
        return sentence;
    }
}
