package com.epam.task.textHanling.interpreter;


public class TerminalExpressionDivide implements Expression {

    public void interpret(ExpressionContext context) {
        context.pushValue(context.popValue() / context.popValue());
    }


}
