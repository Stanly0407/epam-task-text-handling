package com.epam.task.textHanling.parser;

import java.util.ArrayList;

public interface Component {

    void operation();

    void add(Component c);

    Component getChild(int index);

    ArrayList<Component> getComponents();

}
