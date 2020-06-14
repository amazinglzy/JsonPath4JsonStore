package jp4js.query.baseline;

import org.junit.Test;

import jp4js.nf2.rel.NestedRelation;
import jp4js.query.path.Path;
import jp4js.storage.MemStore;
import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Leaf;
import jp4js.nf2.rel.doc.NodeID;
import jp4js.utils.Configuration;
import jp4js.utils.query.PathCompiler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

public class DocScanTest {
    @Test
    public void basic01_() {
        Path path = PathCompiler.fromString("$.name[?(@.first && @.last)].addr");
        DocScan.RelationConstruct construct = new DocScan.RelationConstruct(
            path, 
            new LinkedList<>() {{
                add(
                    new DocNode[] {
                        Leaf.createStringNode(new NodeID(0, 0), "Alice"),
                        Leaf.createStringNode(new NodeID(0, 0), "Bob"),
                        Leaf.createStringNode(new NodeID(0, 0), "what place"),
                    }
                );
            }}
        );
        construct.iterate();
        NestedRelation relation = construct.relation();
        System.out.println(relation.toString());
        assertThat(relation.toString()).isEqualTo(
            "$.name(first, last, addr).addr.[str](DString), $.name(first, last, addr).first.[str](DString), $.name(first, last, addr).last.[str](DString)"
        );
    }

    @Test
    public void basic02_() {
        Path path = PathCompiler.fromString("$[1,2,3].name");
        DocScan.RelationConstruct construct = new DocScan.RelationConstruct(
            path, 
            new LinkedList<>() {{
                add(
                    new DocNode[] {
                        Leaf.createStringNode(new NodeID(0, 0), "Alice"),
                    }
                );
            }}
        );
        construct.iterate();
        NestedRelation relation = construct.relation();
        assertThat(relation.toString()).isEqualTo("$.1, 2, 3(name).name.[str](DString)");
    }

    @Test
    public void basic03_() {
        Configuration configuration = Configuration.defaultConfiguration();
        String json1 = "[1, 2, 3]";
        String json2 = "{\"name\": \"Alice\", \"age\": 20}";
        Object[] json = {
            configuration.jsonProvider().parse(json1),
            configuration.jsonProvider().parse(json2)
        };
        MemStore store = new MemStore();
        for (int i = 0; i < json.length; i++)
            store.add(json[i], configuration);
        Path path = PathCompiler.fromString("$.name");
        DocScan scan = new DocScan(store, path);
        System.out.println(scan.instance().toString());
    }
}