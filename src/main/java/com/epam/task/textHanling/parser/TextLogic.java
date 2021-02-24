package com.epam.task.textHanling.parser;

import org.apache.log4j.Logger;

import java.util.Stack;
import java.util.function.BinaryOperator;


public class TextLogic {

    private static final Logger LOGGER = Logger.getLogger(TextLogic.class);

    private static final String SPACE = " ";

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";

    private BinaryOperator<Integer> operationType;

    public String restore(Component component) {
        StringBuilder textBuilder = new StringBuilder();

        int paragraphsQuantity = component.getComponents().size();
        for (int i = 0; i < paragraphsQuantity; i++) {
            Component paragraph = component.getChild(i);

            int sentencesQuantity = paragraph.getComponents().size();
            for (int j = 0; j < sentencesQuantity; j++) {
                Component sentence = paragraph.getChild(j);

                int lexemesQuantity = sentence.getComponents().size();
                for (int h = 0; h < lexemesQuantity; h++) {

                    Lexeme lexeme = (Lexeme) sentence.getChild(h);
                    if (LexemeType.EXPRESSION.equals(lexeme.getLexemeType())) {
                        calculate(lexeme);    //calculate expression
                    }
                    String word = lexeme.getLexeme();
                    textBuilder.append(word).append(SPACE);
                }
            }
        }
        return new String(textBuilder);
    }


    private void calculate(Lexeme lexeme) {

        String expression = prepareExpressionForCalculating(lexeme.getLexeme());

        char[] charsOfExpressionArray = expression.toCharArray();
        Stack<String> expressionValues = new Stack<>();
        for (char charElement : charsOfExpressionArray) {
            String stringElement = String.valueOf(charElement);
            expressionValues.add(stringElement);
        }

        Stack<String> expressionResult = calculateExpression(expressionValues);

        lexeme.setLexeme(expressionResult.pop());
    }

    private String prepareExpressionForCalculating(String expression) {
        int lastElement = expression.length();

        StringBuilder expressionBuilder = new StringBuilder(expression);
        expressionBuilder.reverse();                     //reverse for stack
        expressionBuilder.deleteCharAt(lastElement - 1); // delete "["
        expressionBuilder.deleteCharAt(0);               // delete "]"
        String clearExpression = new String(expressionBuilder);

        LOGGER.debug("Prepared expression for calculating - " + clearExpression);
        return clearExpression;
    }

    private Stack<String> calculateExpression(Stack<String> expressionValues) {

        while (expressionValues.size() != 1) {
            String firstDigitAsString = expressionValues.pop();
            int firstDigit = Integer.parseInt(firstDigitAsString);
            LOGGER.debug("firstDigit = " + firstDigit);

            String secondDigitAsString = String.valueOf(expressionValues.pop());
            int secondDigit = Integer.parseInt(secondDigitAsString);
            LOGGER.debug("secondDigit = " + secondDigit);

            String operator = expressionValues.pop();
            LOGGER.debug("operator : " + operator);
            qualifyOperationType(operator); //define the type of binary operation

            int calculatingResult = operationType.apply(firstDigit, secondDigit);
            LOGGER.debug("calculatingResult = " + calculatingResult);
            String newDigit = String.valueOf(calculatingResult);
            expressionValues.add(newDigit);

            calculateExpression(expressionValues);
        }

        return expressionValues;
    }


    private void qualifyOperationType(String operator) {
        switch (operator) {
            case PLUS:
                operationType = (firstElement, secondElement) -> firstElement + secondElement;
                break;
            case MINUS:
                operationType = (firstElement, secondElement) -> firstElement - secondElement;
                break;
            case MULTIPLICATION:
                operationType = (firstElement, secondElement) -> firstElement * secondElement;
                break;
            case DIVISION:
                operationType = (firstElement, secondElement) -> firstElement / secondElement;
                break;
        }
    }
}

