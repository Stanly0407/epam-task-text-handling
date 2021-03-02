package com.epam.task.textHanling.interpreter;

import com.epam.task.textHanling.entities.Lexeme;

import java.util.ArrayList;

public class ExpressionCalculator {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";

    private static final String SEPARATOR = "_";

    private ArrayList<Expression> listExpressions;

    public ExpressionCalculator(Lexeme lexeme) {
        listExpressions = new ArrayList<>();
        parseExpression(lexeme);
    }

    public String calculateExpression() {
        ExpressionContext context = new ExpressionContext();

        for (Expression expression : listExpressions) {
            expression.interpret(context);
        }
        Integer expressionResult = context.popValue();
        return String.valueOf(expressionResult);
    }

    public void parseExpression(Lexeme lexeme) {
        String expressionLexeme = lexeme.getLexeme();
        int lastSymbol = expressionLexeme.length();
        String expression = expressionLexeme.substring(1, lastSymbol - 1);

        for (String elementExpression : expression.split(SEPARATOR)) {
            switch (elementExpression) {
                case PLUS:
                    listExpressions.add(new TerminalExpressionPlus());
                    break;
                case MINUS:
                    listExpressions.add(new TerminalExpressionMinus());
                    break;
                case MULTIPLICATION:
                    listExpressions.add(new TerminalExpressionMultiply());
                    break;
                case DIVISION:
                    listExpressions.add(new TerminalExpressionDivide());
                    break;
                default:
                    int number = Integer.parseInt(elementExpression);
                    listExpressions.add(new NonterminalExpression(number));
            }
        }
    }

    public ArrayList<Expression> getListExpression() {
        return listExpressions;
    }

}
