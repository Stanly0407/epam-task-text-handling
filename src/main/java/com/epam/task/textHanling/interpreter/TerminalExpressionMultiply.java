package com.epam.task.textHanling.interpreter;

public class TerminalExpressionMultiply implements Expression {

    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(context.popValue() * context.popValue());
    }

}
