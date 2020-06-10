package jp4js.utils.query;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import jp4js.parser.JsonPathLexer;
import jp4js.parser.JsonPathParser;
import jp4js.query.PathParser;
import jp4js.query.path.Path;
import jp4js.query.path.RootNode;


public class PathCompiler {
    public static Path fromString(String pathString) {
        CharStream s = CharStreams.fromString(pathString);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        RootNode root = (RootNode) new PathParser().visit(parser.jsonBasicPathExpr());
        return new Path(root);
    }

}