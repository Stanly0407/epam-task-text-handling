package com.epam.task.textHanling.entities;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexeme implements Component {

    private String lexeme;
    private LexemeType lexemeType;

    private Lexeme(String lexeme, LexemeType lexemeType) { //private
        this.lexeme = lexeme;
        this.lexemeType = lexemeType;
    }

    public static Lexeme word(String value){
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value){
        return new Lexeme(value, LexemeType.EXPRESSION);
    }


    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Component> getComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearComponent()  {
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
