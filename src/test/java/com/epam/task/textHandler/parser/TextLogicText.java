package com.epam.task.textHandler.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.parser.TextLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TextLogicText {

    // private static final String TEXT = "First one second two. Third three. [1_2_3_4_5_-_*_+_/] result is 5? ";

    private TextLogic textLogic = new TextLogic();
    private Component testedComponent = new Composite();

    private Component paragraphFirst = new Composite();
    private Component paragraphSecond = new Composite();
    private Component sentencesFirst = new Composite();
    private Component sentencesSecond = new Composite();
    private Component sentencesThird = new Composite();
    private Lexeme lexemeA = Lexeme.word("Expression");
    private Lexeme lexemeB = Lexeme.word("result");
    private Lexeme lexemeC = Lexeme.word("is");
    private Lexeme lexemeD = Lexeme.expression("[1_2_3_4_5_-_*_+_/]");
    private Lexeme lexemeE = Lexeme.word("It's");
    private Lexeme lexemeF = Lexeme.word("happy");
    private Lexeme lexemeG = Lexeme.word("ending");

    @Before
    public void createTestingComponent() {
        sentencesFirst.add(lexemeA);
        sentencesFirst.add(lexemeB);
        sentencesSecond.add(lexemeC);
        sentencesSecond.add(lexemeD);
        sentencesThird.add(lexemeE);
        sentencesThird.add(lexemeF);
        sentencesThird.add(lexemeG);
        paragraphFirst.add(sentencesFirst);
        paragraphFirst.add(sentencesSecond);
        paragraphSecond.add(sentencesThird);
        testedComponent.add(paragraphFirst);
        testedComponent.add(paragraphSecond);
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
        Component expectedComponent = new Composite();
        expectedComponent.add(paragraphSecond);
        expectedComponent.add(paragraphFirst);
        // when
        Component actualComponent = textLogic.sortParagraphsByNumberOfSentences(testedComponent);
        // then
        Assert.assertEquals(expectedComponent, actualComponent);
    }

    @Test
    public void shouldSortWordsInSentencesByLength() {
        // given
        Component expectedComponent = new Composite();
        Component paragraphFirst = new Composite();
        Component sentencesFirst = new Composite();
        sentencesFirst.add(lexemeB);
        sentencesFirst.add(lexemeA);
        Component sentencesSecond = new Composite();
        sentencesSecond.add(lexemeC);
        sentencesSecond.add(lexemeD);
        paragraphFirst.add(sentencesFirst);
        paragraphFirst.add(sentencesSecond);
        expectedComponent.add(paragraphFirst);
        expectedComponent.add(paragraphSecond);
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
        List<Component> expectedLexemesList = new ArrayList<>();
        expectedLexemesList.add(lexemeA);
        expectedLexemesList.add(lexemeB);
        expectedLexemesList.add(lexemeC);
        expectedLexemesList.add(lexemeD);
        expectedLexemesList.add(lexemeE);
        expectedLexemesList.add(lexemeF);
        expectedLexemesList.add(lexemeG);
        // when
        List<Component> actualLexemesList = textLogic.getLexemesFromText(testedComponent);
        // then
        Assert.assertEquals(expectedLexemesList, actualLexemesList);
    }

    @Test
    public void getSentencesFromTextShouldReturnListOfSentencesFromComponent() {
        // given
        List<Component> expectedSentencesList = new ArrayList<>();
        expectedSentencesList.add(sentencesFirst);
        expectedSentencesList.add(sentencesSecond);
        expectedSentencesList.add(sentencesThird);
        // when
        List<Component> actualSentencesList = textLogic.getSentencesFromText(testedComponent);
        // then
        Assert.assertEquals(expectedSentencesList, actualSentencesList);
    }

}
