package jp4js.query.baseline;

import org.junit.Test;

import jp4js.nf2.rel.NestedRelation;
import jp4js.query.PathParser;
import jp4js.query.path.Path;
import jp4js.utils.query.PathCompiler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

public class DocScanTest {
    @Test
    public void basic01_() {
        Path path = PathCompiler.fromString("$.name[?(@.first && @.last)].addr");
        DocScan.RelationConstruct construct = new DocScan.RelationConstruct(path, new LinkedList<>());
        construct.iterate();
        NestedRelation relation = construct.relation();
        assertThat(relation.toString()).isEqualTo(
            "$.name(first, last, addr).addr(), $.name(first, last, addr).first(), $.name(first, last, addr).last()"
        );
    }

    @Test
    public void basic02_() {
        Path path = PathCompiler.fromString("$[1,2,3].name");
        DocScan.RelationConstruct construct = new DocScan.RelationConstruct(path, new LinkedList<>());
        construct.iterate();
        NestedRelation relation = construct.relation();
        assertThat(relation.toString()).isEqualTo("$.1, 2, 3(name)(name())");
    }
}