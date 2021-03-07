package com.epam.task.textHanling.entities;

import java.util.List;

public interface Component {

    void add(Component component);

    List<Component> getComponents();

    void clearComponent();

}
