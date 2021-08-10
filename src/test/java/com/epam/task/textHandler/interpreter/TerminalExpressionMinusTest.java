package com.epam.task.textHandler.interpreter;

import com.epam.task.textHanling.interpreter.ExpressionContext;
import com.epam.task.textHanling.interpreter.TerminalExpressionMinus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

public class TerminalExpressionMinusTest {

    private TerminalExpressionMinus terminalExpressionMinus = new TerminalExpressionMinus();

    @Test
    public void interpretShouldReturnDifference() {
        // given
        ArrayDeque<Integer> contextValues = new ArrayDeque<>(Arrays.asList(10, 5));
        ExpressionContext expressionContext = new ExpressionContext(contextValues);
        int expectedResult = 5;

        // when
        terminalExpressionMinus.interpret(expressionContext);
        int actualResult = expressionContext.popValue();

        // then
        Assert.assertEquals(expectedResult, actualResult);
    }

}
