package jp4js.shell.layout;

import java.util.List;
import java.util.LinkedList;

public abstract class ContainersBuilder {
    private WidthAllign width;
    private List<Container> containers;

    public ContainersBuilder() {
        this.containers = new LinkedList<>();
        this.width = null;
    }

    public ContainersBuilder(WidthAllign width) {
        this.width = width;
        this.containers = new LinkedList<>();
    }

    public void add(Container container) {
        this.containers.add(container);
    }

    public List<Container> containers() {
        return this.containers;
    }

    public WidthAllign width() {
        return this.width;
    }


    public abstract Container build();
}