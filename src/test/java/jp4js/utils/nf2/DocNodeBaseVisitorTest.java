package jp4js.utils.nf2;

import jp4js.JsonData;
import jp4js.nf2.rel.doc.DocNode;
import jp4js.nf2.rel.doc.Internal;
import jp4js.nf2.rel.doc.Leaf;
import jp4js.utils.Configuration;
import jp4js.utils.TransUtils;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DocNodeBaseVisitorTest {
    public static class Visitor extends DocNodeBaseVisitor<String> {
        public String visitPropertyNode(Internal.PropertyNode node) {
            String ret = "";
            for (String fieldname: node)  {
                if (ret.length() != 0) ret += ", ";
                ret += fieldname + "(" + this.visit(node.get(fieldname)) +")";
            }
            return ret;
        }

        public String visitIndexNode(Internal.IndexNode node) {
            String ret = "";
            for (DocNode indexNode: node) {
                if (ret.length() != 0) ret += ", ";
                ret += this.visit(indexNode);
            }
            return ret;
        }

        public String visitStringNode(Leaf.StringNode node) {
            return node.toString();
        }

        public String visitDoubleNode(Leaf.DoubleNode node) {
            return node.toString();
        }

        public String visitIntNode(Leaf.IntNode node) {
            return node.toString();
        }
    }

    @Test
    public void basic01_() {
        Configuration configuration = Configuration.defaultConfiguration();
        Object json = JsonData.createJson(JsonData.EXAPMLE01, configuration);
        DocNode node = TransUtils.trans(json, configuration);
        assertThat(new Visitor().visit(node)).isEqualTo(node.toString());
    }
}