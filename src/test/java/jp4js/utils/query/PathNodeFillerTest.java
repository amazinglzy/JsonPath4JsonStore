package jp4js.utils.query;

import org.junit.Test;

import jp4js.nf2.rel.BasicType;
import jp4js.nf2.rel.NestedRelation;
import jp4js.nf2.rel.NestedRelationBuilder;
import jp4js.storage.doc.DocNode;
import jp4js.storage.doc.Internal;
import jp4js.storage.doc.Leaf;
import jp4js.storage.doc.NodeID;
import jp4js.utils.nf2.PathNodeFiller;

import static org.assertj.core.api.Assertions.assertThat;

public class PathNodeFillerTest {
    @Test
    public void basic01_() {
        DocNode node = new Internal.PropertyNodeBuilder() {{
            put(
                "name", 
                new Leaf.StringNode(new NodeID(0, 0), BasicType.createDString("testdata"))
            );
        }}.build(new NodeID(0, 0));
        NestedRelation rel = new NestedRelationBuilder()
            .put("$.name.[str]", BasicType.dString)
            .build();
        NestedRelation.InstanceBuilder builder = rel.builder();
        PathNodeFiller filler = new PathNodeFiller("$", builder);
        builder.begin();
        filler.fill(node);
        builder.end();
        // System.out.println(builder.build().toString());
        assertThat(builder.build().toString()).isEqualTo("[(\"testdata\")]");
    }
}