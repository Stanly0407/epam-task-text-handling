package com.epam.task.textHanling.parser;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexeme implements Component {
    private static final Logger LOGGER = Logger.getLogger(Lexeme.class);

    private static final String EXPRESSION_PATTERN = "^(\\[)+(.+)(])+$";

    private String lexeme;
    private LexemeType lexemeType;

    public Lexeme(String lexeme, LexemeType lexemeType) {
        this.lexeme = lexeme;
        this.lexemeType = lexemeType;
    }

    public Lexeme(String lexeme) {
        this.lexeme = lexeme;
    }


    @Override
    public void operation() {
        if (isExpression(this.lexeme)) {
            this.setLexemeType(LexemeType.EXPRESSION);
            LOGGER.debug("There was found expression in text : " + this.lexeme);
        } else {
            this.setLexemeType(LexemeType.WORD);
        }
    }

    public boolean isExpression(String lexeme) {
        Pattern linePattern = Pattern.compile(EXPRESSION_PATTERN);
        Matcher matcher = linePattern.matcher(lexeme);
        return matcher.matches();
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Component> getComponents() {
        throw new UnsupportedOperationException();
    }


    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public void setLexemeType(LexemeType lexemeType) {
        this.lexemeType = lexemeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lexeme lexeme1 = (Lexeme) o;
        return Objects.equals(lexeme, lexeme1.lexeme) &&
                lexemeType == lexeme1.lexemeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexeme, lexemeType);
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "lexeme='" + lexeme + '\'' +
                ", lexemeType=" + lexemeType +
                '}';
    }
}
