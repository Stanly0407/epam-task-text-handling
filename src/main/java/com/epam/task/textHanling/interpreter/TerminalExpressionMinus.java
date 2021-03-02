package com.epam.task.textHanling.interpreter;

public class TerminalExpressionMinus implements Expression {

    @Override
    public void interpret(ExpressionContext context) {
        int firstNumber = context.popValue();
        int secondNumber = context.popValue();
        context.pushValue(firstNumber - secondNumber);
    }

}
