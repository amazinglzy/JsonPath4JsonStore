package jp4js.shell.layout;

import java.util.List;
import java.util.LinkedList;

public abstract class ContainersBuilder {
    private SharedWidth width;
    private List<Container> containers;

    public ContainersBuilder() {
        this.containers = new LinkedList<>();
        this.width = null;
    }

    public ContainersBuilder(SharedWidth width) {
        this.width = width;
        this.containers = new LinkedList<>();
    }

    public void add(Container container) {
        this.containers.add(container);
    }

    public List<Container> containers() {
        return this.containers;
    }

    public SharedWidth width() {
        return this.width;
    }


    public abstract Container build();
}