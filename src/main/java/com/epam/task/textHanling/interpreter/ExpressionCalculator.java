package com.epam.task.textHanling.interpreter;

import com.epam.task.textHanling.entities.Lexeme;

import java.util.ArrayList;

public class ExpressionCalculator {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";

    private static final String SEPARATOR = "_";

    private ArrayList<Expression> listExpression;

    public ExpressionCalculator(Lexeme lexeme) {
        listExpression = new ArrayList<>();
        parseExpression(lexeme);
    }

    private void parseExpression(Lexeme lexeme) {
        String expressionLexeme = lexeme.getLexeme();
        int lastSymbol = expressionLexeme.length();
        String expression = expressionLexeme.substring(1, lastSymbol-1);
        for (String elementExpression : expression.split(SEPARATOR)) {

            switch (elementExpression) {
                case PLUS:
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case MINUS:
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case MULTIPLICATION:
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case DIVISION:
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    int number = Integer.parseInt(elementExpression);
                    listExpression.add(new NonterminalExpression(number));
            }
        }
    }

    public String calculateExpression() {
        ExpressionContext context = new ExpressionContext();

        for (Expression expression : listExpression) {
            expression.interpret(context);
        }
        Number expressionResult = context.popValue();
        return String.valueOf(expressionResult);
    }

}
