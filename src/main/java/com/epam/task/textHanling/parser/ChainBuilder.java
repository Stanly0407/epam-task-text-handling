package com.epam.task.textHanling.parser;

public class ChainBuilder {

    public AbstractParser build() {
        return new TextParser(new ParagraphParser(new SentenceParser()));
    }
}
