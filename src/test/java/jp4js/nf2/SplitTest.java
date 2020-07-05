package jp4js.nf2;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import jp4js.data.BaseDataSuite;
import jp4js.data.Goessener;
import jp4js.data.JsonArray;
import jp4js.data.JsonArrayMulti;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.nf2.op.Split;
import jp4js.nf2.op.structure.StructureList;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitTest {
    public void forDataSuite(BaseDataSuite suite) {
        for (int i = 0; i < suite.querySize(); i++) {
            StructureList lst = suite.query(i);
            Split split = new Split(suite.instance(), lst);
            try {
                Match match = split.open();
                assertThat(match.isValid()).isTrue();
                System.out.println(match.header().toString());
                System.out.println(match.body().toString());
                if (i < suite.res().length) {
                    assertThat(match.body().toString()).isEqualTo(
                        suite.res()[i]
                    );
                }
            } catch (Split.MatchException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void basic01_() {
        DType.Instance instance = new Goessener().instance();

        Split split = new Split(instance, new Goessener().query(0));
        try {
            Match match = split.open();
            assertThat(match.isValid()).isTrue();
            // System.out.println(match.header().toString());
            // System.out.println(match.body().toString());
            assertThat(match.header().toString()).isEqualTo(
                "(store(book[author()]))"
            );
            assertThat(match.body().toString()).isEqualTo(
                "(([(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]))"
            );
        } catch (Split.MatchException e) {
            assertFalse(e.toString(), true);
        }
    }

    @Test
    public void basic02_DataSuite_Goessener() {
        forDataSuite(new Goessener());
    }

    @Test
    public void basic03_DataSuite_JsonArray() {
        forDataSuite(new JsonArray());
    }

    @Test
    public void basic03_DataSuite_JsonArrayMulti() {
        forDataSuite(new JsonArrayMulti());
    }

    @Test
    public void basic04_DataSuite_JsonPathWebsite() {
        forDataSuite(new JsonPathWebsite());
    }

    @Test
    public void basic05_DataSuite_NestedFieldname() {
        forDataSuite(new NestedFieldname());
    }

    @Test
    public void basic05_DataSuite_NestedSameFieldname() {
        forDataSuite(new NestedSameFieldname());
    }
}