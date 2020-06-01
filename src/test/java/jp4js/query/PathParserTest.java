package jp4js.query;

import jp4js.parser.JsonPathLexer;
import jp4js.parser.JsonPathParser;
import jp4js.query.path.RootNode;

import org.antlr.v4.runtime.*;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PathParserTest {
    
    @Test
    public void basic01_() {
        CharStream s = CharStreams.fromString("$.name[?(@.first && @.last)].address[*]");
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        RootNode root = (RootNode) new PathParser().visit(parser.jsonBasicPathExpr());
        System.out.println(root.toString());
        assertThat(root.toString()).isEqualTo("(name(first, last, address(*)))");
    }
}