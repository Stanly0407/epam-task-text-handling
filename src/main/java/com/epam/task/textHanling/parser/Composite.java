package com.epam.task.textHanling.parser;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;

public class Composite implements Component {
    private static final Logger LOGGER = Logger.getLogger(Composite.class);

    private ArrayList<Component> components = new ArrayList<>();

    @Override
    public ArrayList<Component> getComponents() {
        return components;
    }

    @Override
    public void operation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Component component) {
        LOGGER.debug("Adding component : " + component);
        components.add(component);
    }


    @Override
    public Component getChild(int index) {
        Component component = components.get(index);
        LOGGER.debug("Getting composite" + component);
        return component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Composite composite = (Composite) o;
        return Objects.equals(components, composite.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "components=" + components +
                '}';
    }
}
