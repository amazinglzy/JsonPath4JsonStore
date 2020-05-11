package jp4js.query;

import jp4js.parser.*;
import jp4js.query.RecordSet.Record;
import jp4js.query.baseline.NaiveAbsolute;
import jp4js.query.navigation.NavigationD;
import jp4js.query.navigation.NavigationS;
import jp4js.query.navigation.NavigationPlanOp;
import jp4js.utils.Configuration;
import jp4js.index.IndexContext;
import jp4js.index.Indexer;
import jp4js.index.node.LabelNode;
import jp4js.utils.iter.Iter2Iterator;

// import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.*;

import java.util.Iterator;

public class JsonPath {
    static public Iterator<Record> evaluateByScan(String path, Object json, Configuration configuration) {
        CharStream s = CharStreams.fromString(path);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        NaiveAbsolute visitor = new NaiveAbsolute(json, configuration);
        visitor.visit(parser.jsonBasicPathExpr());
        return new Iter2Iterator<>(visitor.results());
    }

    static public Iterator<Record> evaluateByMergeJoinD(String path, Object json, Configuration configuration) {
        CharStream s = CharStreams.fromString(path);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        IndexContext indexContext = Indexer.index(json, configuration);

        NavigationD visitor = new NavigationD(indexContext);
        visitor.visit(parser.jsonBasicPathExpr());

        PlanOperator<LabelNode> op = 
            NavigationPlanOp.createItem2LabelNode(
                GeneralPlanOp.createConcat(visitor.operators())
            );
        return new NodeWrapper(op.iterator(), new RecordSet(configuration));
    }

    static public Iterator<Record> evaluateByMergeJoinS(String path, Object json, Configuration configuration) {
        CharStream s = CharStreams.fromString(path);
        JsonPathLexer lexer = new JsonPathLexer(s);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JsonPathParser parser = new JsonPathParser(tokens);
        IndexContext indexContext = Indexer.index(json, configuration);

        NavigationS visitor = new NavigationS(indexContext);
        visitor.visit(parser.jsonBasicPathExpr());

        PlanOperator<LabelNode> op = visitor.operator();
        return new NodeWrapper(op.iterator(), new RecordSet(configuration));
    }
}