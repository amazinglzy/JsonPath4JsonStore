package jp4js.utils.query;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import jp4js.algebra.op.structure.StructureList;
import jp4js.query.parser.JsonPathLexer;
import jp4js.query.parser.JsonPathParser;
import jp4js.query.PathParser;


public class PathCompiler {
    public static StructureList fromString(String pathString) {
        CharStream s = CharStreams.fromString(pathString);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        StructureList ret = new PathParser().visit(parser.jsonBasicPathExpr());
        return ret;
    }

}