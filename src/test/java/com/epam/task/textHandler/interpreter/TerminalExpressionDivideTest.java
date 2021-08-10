package com.epam.task.textHandler.interpreter;

import com.epam.task.textHanling.interpreter.ExpressionContext;
import com.epam.task.textHanling.interpreter.TerminalExpressionDivide;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

public class TerminalExpressionDivideTest {

    private TerminalExpressionDivide terminalExpressionDivide = new TerminalExpressionDivide();

    @Test
    public void interpretShouldReturnDivisionResult() {
        // given
        ArrayDeque<Integer> contextValues = new ArrayDeque<>(Arrays.asList(10, 5));
        ExpressionContext expressionContext = new ExpressionContext(contextValues);
        int expectedResult = 2;

        // when
        terminalExpressionDivide.interpret(expressionContext);
        int actualResult = expressionContext.popValue();

        // then
        Assert.assertEquals(expectedResult, actualResult);
    }

}
