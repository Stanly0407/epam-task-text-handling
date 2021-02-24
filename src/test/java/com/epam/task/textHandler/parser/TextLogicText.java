package com.epam.task.textHandler.parser;

import com.epam.task.textHanling.parser.TextLogic;
import com.epam.task.textHanling.parser.Component;
import com.epam.task.textHanling.parser.Composite;
import com.epam.task.textHanling.parser.Lexeme;
import com.epam.task.textHanling.parser.LexemeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextLogicText {

    //    private static final String TEXT = "First one. [12+4*3+] expression. \nSingle. Four, end... \n";
    private static final String EXPECTED_TEXT = "First one 15 expression Single Four end ";
    private Component testedComponent = new Composite();
    private TextLogic textLogic = new TextLogic();

    //given
    @Before
    public void createTestingComponent() {
        Lexeme lexemeA = new Lexeme("First", LexemeType.WORD);
        Lexeme lexemeB = new Lexeme("one", LexemeType.WORD);
        Lexeme lexemeC = new Lexeme("[12+4*3+]", LexemeType.EXPRESSION);
        Lexeme lexemeD = new Lexeme("expression", LexemeType.WORD);
        Lexeme lexemeE = new Lexeme("Single", LexemeType.WORD);
        Lexeme lexemeF = new Lexeme("Four", LexemeType.WORD);
        Lexeme lexemeG = new Lexeme("end", LexemeType.WORD);

        Component sentencesFirst = new Composite();
        sentencesFirst.add(lexemeA);
        sentencesFirst.add(lexemeB);
        Component sentencesSecond = new Composite();
        sentencesSecond.add(lexemeC);
        sentencesSecond.add(lexemeD);
        Component sentencesThird = new Composite();
        sentencesThird.add(lexemeE);
        Component sentencesFourth = new Composite();
        sentencesFourth.add(lexemeF);
        sentencesFourth.add(lexemeG);

        Component paragraphFirst = new Composite();
        paragraphFirst.add(sentencesFirst);
        paragraphFirst.add(sentencesSecond);
        Component paragraphSecond = new Composite();
        paragraphSecond.add(sentencesThird);
        paragraphSecond.add(sentencesFourth);

        testedComponent.add(paragraphFirst);
        testedComponent.add(paragraphSecond);
    }


    @Test
    public void restoreShouldReturnText() {
        // when
        String actualResult = textLogic.restore(testedComponent);
        // then
        Assert.assertEquals(EXPECTED_TEXT, actualResult);
    }

}
