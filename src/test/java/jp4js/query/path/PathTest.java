package jp4js.query.path;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

public class PathTest {
    @Test
    public void basic01_() {
        RootNode root = new PathBuilder()
            .enterPropertyNode(new LinkedList<>() {{ add("name"); }})
                .enterPropertyNode(new LinkedList<>() {{add("first");}})
                .exit()
                .enterPropertyNode(new LinkedList<>() {{add("last");}})
                .exit()
            .exit()
            .build();
        assertThat(root.toString()).isEqualTo("(name(first, last))");
    }
}