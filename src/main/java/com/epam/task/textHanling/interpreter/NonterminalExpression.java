package com.epam.task.textHanling.interpreter;

public class NonterminalExpression implements Expression {

    private int number;

    public NonterminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(number);
    }

}
