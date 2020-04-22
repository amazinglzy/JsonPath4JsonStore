package jp4js.query.scan;

import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;
import jp4js.parser.*;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.*;

import java.util.Iterator;

public class JsonPath {
    static public Iterator<Record> evaluate(String path, Object json, Configuration configuration) {
        CharStream s = CharStreams.fromString(path);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        JsonPathScanListener listener = new JsonPathScanListener(json, configuration);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parser.jsonBasicPathExpr());
        return listener.results();
    }
}