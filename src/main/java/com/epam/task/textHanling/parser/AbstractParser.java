package com.epam.task.textHanling.parser;

import com.epam.task.textHanling.entities.Component;
import com.epam.task.textHanling.entities.Composite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractParser implements Parser {

    private Parser successor;

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }

    public AbstractParser() {
    }

    protected Parser getSuccessor() {
        return successor;
    }

    protected abstract String getSeparator();

    @Override
    public Component parse(String input) {
        String separator = getSeparator();

        String[] textElements = input.split(separator);

        Parser successor = getSuccessor();

        List<Component> components = Arrays.stream(textElements)
                .map(successor::parse)
                .collect(Collectors.toList());

        return new Composite(components);
    }

}

