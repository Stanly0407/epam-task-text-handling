package com.epam.task.textHanling.entities;

import java.util.ArrayList;

public interface Component {

    void add(Component component);

    ArrayList<Component> getComponents();

    void clearComponent();

}
