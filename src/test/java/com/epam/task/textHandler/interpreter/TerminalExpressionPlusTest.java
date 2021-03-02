package com.epam.task.textHandler.interpreter;

import com.epam.task.textHanling.interpreter.ExpressionContext;
import com.epam.task.textHanling.interpreter.TerminalExpressionPlus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

public class TerminalExpressionPlusTest {

    private TerminalExpressionPlus terminalExpressionPlus = new TerminalExpressionPlus();

    @Test
    public void interpretShouldReturnSum() {
        // given
        ArrayDeque<Integer> contextValues = new ArrayDeque<>(Arrays.asList(10, 5));
        ExpressionContext expressionContext = new ExpressionContext(contextValues);
        int expectedResult = 15;

        // when
        terminalExpressionPlus.interpret(expressionContext);
        int actualResult = expressionContext.popValue();

        // then
        Assert.assertEquals(expectedResult, actualResult);
    }

}
