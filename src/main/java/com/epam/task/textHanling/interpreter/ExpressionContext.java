package com.epam.task.textHanling.interpreter;

import java.util.ArrayDeque;

public class ExpressionContext {

    private ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    public ExpressionContext(ArrayDeque<Integer> contextValues) {
        this.contextValues = contextValues;
    }

    public ExpressionContext() {
    }

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        this.contextValues.push(value);
    }

}
