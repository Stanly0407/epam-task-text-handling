package com.epam.task.textHanling.interpreter;

import com.epam.task.textHanling.interpreter.Expression;
import com.epam.task.textHanling.interpreter.ExpressionContext;

public class TerminalExpressionMinus implements Expression {

    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(context.popValue() - context.popValue());
    }

}
