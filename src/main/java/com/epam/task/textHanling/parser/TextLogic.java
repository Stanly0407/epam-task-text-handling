package com.epam.task.textHanling.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;
import com.epam.task.textHanling.entities.Lexeme;
import com.epam.task.textHanling.interpreter.ExpressionCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TextLogic {

    private static final String SPACE = " ";

    public String restore(Component component) {
        StringBuilder textBuilder = new StringBuilder();
        List<Component> lexemes = getLexemesFromText(component);

        for (Component lexemeElement : lexemes) {
            Lexeme lexeme = (Lexeme) lexemeElement;
            calculateIfLexemeExpression(lexeme);
            String word = lexeme.getLexeme();
            textBuilder.append(word).append(SPACE);
        }
        return new String(textBuilder);
    }


    public Component sortParagraphsByNumberOfSentences(Component component) {
        List<Component> paragraphs = component.getComponents();

        paragraphs.sort(Comparator.comparingInt((Component componentElement) -> (componentElement.getComponents().size())));

        Component modifiedText = new Composite();
        for (Component paragraph : paragraphs) {
            modifiedText.add(paragraph);
        }
        return modifiedText;
    }


    public Component sortWordsInSentencesByLength(Component text) {
        List<Component> sentences = getSentencesFromText(text);

        for (Component sentence : sentences) {
            List<Component> lexemeComponents = sentence.getComponents();
            List<Lexeme> lexemes = new ArrayList<>();
            for (Component lexemeElement : lexemeComponents) {
                Lexeme lexeme = (Lexeme) lexemeElement;
                lexemes.add(lexeme);
            }

            lexemes.sort(Comparator.comparingInt((Lexeme lexeme) -> (lexeme.getLexeme().length())));

            // clean up the component and rewrite sorted lexemes in sentence:
            sentence.clearComponent();
            for (Lexeme lexeme : lexemes) {
                sentence.add(lexeme);
            }
        }
        return text;
    }

    public void calculateIfLexemeExpression(Lexeme lexeme) {
        LexemeType lexemeType = lexeme.getLexemeType();

        if (lexemeType.equals(LexemeType.EXPRESSION)) {
            ExpressionCalculator expressionCalculator = new ExpressionCalculator(lexeme);
            String expressionResult = expressionCalculator.calculateExpression();
            lexeme.setLexeme(expressionResult);
        }
    }

    public List<Component> getLexemesFromText(Component text) {
        List<Component> sentences = getSentencesFromText(text);
        List<Component> lexemes = new ArrayList<>();
        for (Component sentence : sentences) {
            lexemes.addAll(sentence.getComponents());
        }
        return lexemes;
    }

    public List<Component> getSentencesFromText(Component component) {
        List<Component> paragraphs = component.getComponents();
        List<Component> sentences = new ArrayList<>();
        for (Component paragraph : paragraphs) {
            sentences.addAll(paragraph.getComponents());
        }
        return sentences;
    }

}

