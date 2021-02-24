package com.epam.task.textHanling.parser;

public class ChainBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(new SentenceParser()));
    }
}
