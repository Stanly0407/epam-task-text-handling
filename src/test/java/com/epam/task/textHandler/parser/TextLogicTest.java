package com.epam.task.textHandler.parser;


import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.parser.TextLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextLogicTest {

    private TextLogic textLogic = new TextLogic();


    private Lexeme lexemeA = Lexeme.word("Expression");
    private Lexeme lexemeB = Lexeme.word("result");
    private Lexeme lexemeC = Lexeme.word("is");
    private Lexeme lexemeD = Lexeme.expression("[1_2_3_4_5_-_*_+_/]");
    private Lexeme lexemeE = Lexeme.word("It's");
    private Lexeme lexemeF = Lexeme.word("happy");
    private Lexeme lexemeG = Lexeme.word("ending");

    private Component sentencesFirst = new Composite();
    private Component sentencesSecond = new Composite();
    private Component sentencesThird = new Composite();

    private Component paragraphFirst = new Composite(Arrays.asList(sentencesFirst, sentencesSecond));
    private Component paragraphSecond = new Composite(Arrays.asList(sentencesThird));
    private Component testedComponent = new Composite(Arrays.asList(paragraphFirst, paragraphSecond));

    @Before
    public void createTestingComponent() {
        sentencesFirst.add(lexemeA);
        sentencesFirst.add(lexemeB);
        sentencesSecond.add(lexemeC);
        sentencesSecond.add(lexemeD);
        sentencesThird.add(lexemeE);
        sentencesThird.add(lexemeF);
        sentencesThird.add(lexemeG);
    }

    @Test
    public void restoreShouldReturnTextWithCalculatedExpressions() {
        //given
        String expectedText = "Expression result is 5 It's happy ending ";
        // when
        String actualResult = textLogic.restore(testedComponent);
        // then
        Assert.assertEquals(expectedText, actualResult);
    }

    @Test
    public void shouldSortParagraphsByNumberOfSentences() {
        // given
        Component expectedComponent = new Composite(Arrays.asList(paragraphSecond, paragraphFirst)); // new order
        // when
        Component actualComponent = textLogic.sortParagraphsByNumberOfSentences(testedComponent);
        // then
        Assert.assertEquals(expectedComponent, actualComponent);
    }

    @Test
    public void shouldSortWordsInSentencesByLength() {
        // given
        Component sentencesFirst = new Composite(Arrays.asList(lexemeB, lexemeA)); // new order
        Component sentencesSecond = new Composite(Arrays.asList(lexemeC, lexemeD));
        Component paragraphFirst = new Composite(Arrays.asList(sentencesFirst, sentencesSecond));
        Component expectedComponent = new Composite(Arrays.asList(paragraphFirst, paragraphSecond));
        // when
        Component actualComponent = textLogic.sortWordsInSentencesByLength(testedComponent);
        // then
        Assert.assertEquals(expectedComponent, actualComponent);
    }

    @Test
    public void calculateIfLexemeExpressionShouldModifyLexeme() {
        // given
        Lexeme testedLexeme = Lexeme.expression("[1_2_3_4_5_-_*_+_/]");
        Lexeme expectedLexeme = Lexeme.expression("5");
        // when
        textLogic.calculateIfLexemeExpression(testedLexeme);
        // then
        Assert.assertEquals(expectedLexeme, testedLexeme);
    }

    @Test
    public void getLexemesFromTextShouldReturnListOfLexemesFromComponent() {
        // given
        List<Component> expectedLexemesList = new ArrayList<>(Arrays.asList(lexemeA, lexemeB, lexemeC, lexemeD, lexemeE, lexemeF, lexemeG));
        // when
        List<Component> actualLexemesList = textLogic.getLexemesFromText(testedComponent);
        // then
        Assert.assertEquals(expectedLexemesList, actualLexemesList);
    }

    @Test
    public void getSentencesFromTextShouldReturnListOfSentencesFromComponent() {
        // given
        List<Component> expectedSentencesList = new ArrayList<>(Arrays.asList(sentencesFirst, sentencesSecond, sentencesThird));
        // when
        List<Component> actualSentencesList = textLogic.getSentencesFromText(testedComponent);
        // then
        Assert.assertEquals(expectedSentencesList, actualSentencesList);

    }
}