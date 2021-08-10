package com.epam.task.textHandler.interpreter;

import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.interpreter.ExpressionCalculator;
import com.epam.task.textHanling.interpreter.NonterminalExpression;
import com.epam.task.textHanling.interpreter.TerminalExpressionPlus;
import org.junit.Assert;
import org.junit.Test;


public class ExpressionCalculatorTest {

    private Lexeme lexeme = Lexeme.expression("[1_2_+]");

    @Test
    public void shouldCalculateExpression() {
        // given
        ExpressionCalculator expressionCalculator = new ExpressionCalculator(lexeme);
        String expectedResult = "3";
        // when
        String actualResult = expressionCalculator.calculateExpression();
        // then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void parseExpressionShouldCreateListExpressions() {
        // when
        ExpressionCalculator actualExpressionCalculator = new ExpressionCalculator(lexeme);
        // then
        Assert.assertTrue(actualExpressionCalculator.getListExpression().get(0) instanceof NonterminalExpression);
        Assert.assertTrue(actualExpressionCalculator.getListExpression().get(1) instanceof NonterminalExpression);
        Assert.assertTrue(actualExpressionCalculator.getListExpression().get(2) instanceof TerminalExpressionPlus);
    }

}
