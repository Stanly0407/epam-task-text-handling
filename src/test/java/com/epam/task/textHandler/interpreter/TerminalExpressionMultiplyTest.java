package com.epam.task.textHandler.interpreter;

import com.epam.task.textHanling.interpreter.ExpressionContext;
import com.epam.task.textHanling.interpreter.TerminalExpressionMultiply;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

public class TerminalExpressionMultiplyTest {

    private TerminalExpressionMultiply terminalExpressionMultiply = new TerminalExpressionMultiply();

    @Test
    public void interpretShouldReturnMultiplyingResult() {
        // given
        ArrayDeque<Integer> contextValues = new ArrayDeque<>(Arrays.asList(10, 5));
        ExpressionContext expressionContext = new ExpressionContext(contextValues);
        int expectedResult = 50;

        // when
        terminalExpressionMultiply.interpret(expressionContext);
        int actualResult = expressionContext.popValue();

        // then
        Assert.assertEquals(expectedResult, actualResult);
    }

}
