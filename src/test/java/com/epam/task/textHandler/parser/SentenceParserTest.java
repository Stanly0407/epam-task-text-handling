package com.epam.task.textHandler.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceParserTest {

    private static final String SENTENCE_TEXT = "Expression [1_2_3_4_5_-_*_+_/]";
    private static final String TESTED_WORD_LEXEME = "Expression";
    private static final String TESTED_EXPRESSION_LEXEME = "[1_2_3_4_5_-_*_+_/]";

    private Lexeme lexemeWord = Lexeme.word(TESTED_WORD_LEXEME);
    private Lexeme lexemeExpression = Lexeme.expression(TESTED_EXPRESSION_LEXEME);
    private SentenceParser sentenceParser = new SentenceParser();

    @Test
    public void parseShouldReturnComponentsLexemes() {
        // given
        Component expectedResult = new Composite(Arrays.asList(lexemeWord, lexemeExpression));
        //when
        Component actualResult = sentenceParser.parse(SENTENCE_TEXT);
        //then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void createLexemeShouldCreateLexemeWord() {
        // when
        Lexeme actualResult = sentenceParser.createLexeme(TESTED_WORD_LEXEME);
        // then
        Assert.assertEquals(lexemeWord, actualResult);
    }

    @Test
    public void createLexemeShouldCreateLexemeExpression() {
        // when
        Lexeme actualResult = sentenceParser.createLexeme(TESTED_EXPRESSION_LEXEME);
        // then
        Assert.assertEquals(lexemeExpression, actualResult);
    }

    @Test
    public void isExpressionShouldReturnTrue() {
        // when
        boolean actualResult = sentenceParser.isExpression(TESTED_EXPRESSION_LEXEME);
        // then
        Assert.assertTrue(actualResult);
    }

    @Test
    public void isExpressionShouldReturnFalse() {
        // when
        boolean actualResult = sentenceParser.isExpression(TESTED_WORD_LEXEME);
        // then
        Assert.assertFalse(actualResult);
    }

}
