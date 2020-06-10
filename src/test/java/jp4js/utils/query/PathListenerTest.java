package jp4js.utils.query;

import jp4js.query.path.ArrayNode;
import jp4js.query.path.DecendentNode;
import jp4js.query.path.Path;
import jp4js.query.path.PropertyNode;
import jp4js.query.path.RootNode;
import jp4js.query.path.WildcardNode;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PathListenerTest {
    public static class ExamplePathListener extends PathBaseListener {
        private String repr;

        public ExamplePathListener(Path path) {
            super(path);
            this.repr = "";
        }

        public String repr() {
            return this.repr;
        }

        public void enterRootNode(RootNode node) {
            this.repr += "enter(" + node.toString() + ")\n";
        }

        public void enterPropertyNode(PropertyNode node) {
            this.repr += "enter(" + node.toString() + ")\n";
        }

        public void enterArrayNode(ArrayNode node) {
            this.repr += "enter(" + node.toString() + ")\n";
        }

        public void enterWildcardNode(WildcardNode node) {
            this.repr += "enter(" + node.toString() + ")\n";
        }

        public void enterDecendentNode(DecendentNode node) {
            this.repr += "enter(" + node.toString() + ")\n";
        }

        public void exitRootNode(RootNode node) {
            this.repr += "exit(" + node.toString() + ")\n";
        }

        public void exitPropertyNode(PropertyNode node) {
            this.repr += "exit(" + node.toString() + ")\n";
        }

        public void exitArrayNode(ArrayNode node) {
            this.repr += "exit(" + node.toString() + ")\n";
        }

        public void exitWildcardNode(WildcardNode node) {
            this.repr += "exit(" + node.toString() + ")\n";
        }

        public void exitDecendentNode(DecendentNode node) {
            this.repr += "exit(" + node.toString() + ")\n";
        }
    }

    @Test
    public void basic01_() {
        Path path = PathCompiler.fromString("$.name[*]");
        ExamplePathListener listener = new ExamplePathListener(path);
        listener.iterate();
        assertThat(listener.repr()).isEqualTo(
            "enter((name(*)))\n"+
            "enter(name(*))\n"+
            "enter(*)\n"+
            "exit(*)\n"+
            "exit(name(*))\n"+
            "exit((name(*)))\n"
        );
    }
}