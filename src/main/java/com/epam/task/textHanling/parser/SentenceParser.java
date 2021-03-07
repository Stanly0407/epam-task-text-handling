package com.epam.task.textHanling.parser;


import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final Logger LOGGER = Logger.getLogger(SentenceParser.class);

    private static final String LEXEME_SEPARATOR = "\\s";
    private static final String EXPRESSION_PATTERN = "^(\\[)+(.+)(])+$";

    public SentenceParser() {
    }

    @Override
    protected String getSeparator() {
        return LEXEME_SEPARATOR;
    }

    @Override
    public Component parse(String input) {
        String[] lexemes = input.split(LEXEME_SEPARATOR);

        Component sentence = new Composite();

        Arrays.stream(lexemes).forEach(lexeme -> sentence.add(createLexeme(lexeme)));

        return sentence;
    }

    public Lexeme createLexeme(String lexeme) {
        Lexeme lexemeElement;

        if (isExpression(lexeme)) {
            lexemeElement = Lexeme.expression(lexeme);
            LOGGER.debug("There was found expression in text : " + lexeme);
        } else {
            lexemeElement = Lexeme.word(lexeme);
        }
        return lexemeElement;
    }

    public boolean isExpression(String lexeme) {
        Pattern linePattern = Pattern.compile(EXPRESSION_PATTERN);
        Matcher matcher = linePattern.matcher(lexeme);
        return matcher.matches();
    }

}
