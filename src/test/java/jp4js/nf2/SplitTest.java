package jp4js.nf2;

import static org.junit.Assert.assertFalse;

import jp4js.data.BaseDataSuite;
import jp4js.data.Goessener;
import jp4js.data.JsonArray;
import jp4js.data.JsonArrayMulti;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.data.XMarkSample;
import jp4js.nf2.op.Split;
import jp4js.nf2.op.SSplit;
import jp4js.nf2.op.structure.StructureList;
import jp4js.storage.IndexContext;
import jp4js.storage.Indexer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SplitTest {
    public void forDataSuiteSplit(BaseDataSuite suite) {
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

    public void forDataSuiteSSplit(BaseDataSuite suite) {
        IndexContext indexContext = Indexer.index(suite.instance());
        for (int i = 0; i < suite.querySize(); i++) {
            StructureList lst = suite.query(i);
            SSplit split = new SSplit(indexContext, lst);
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

    /* Split */

    @Test
    public void basic02_DataSuiteSplit_Goessener() {
        forDataSuiteSplit(new Goessener());
    }

    @Test
    public void basic03_DataSuiteSplit_JsonArray() {
        forDataSuiteSplit(new JsonArray());
    }

    @Test
    public void basic04_DataSuiteSplit_JsonArrayMulti() {
        forDataSuiteSplit(new JsonArrayMulti());
    }

    @Test
    public void basic05_DataSuiteSplit_JsonPathWebsite() {
        forDataSuiteSplit(new JsonPathWebsite());
    }

    @Test
    public void basic06_DataSuiteSplit_NestedFieldname() {
        forDataSuiteSplit(new NestedFieldname());
    }

    @Test
    public void basic07_DataSuiteSplit_NestedSameFieldname() {
        forDataSuiteSplit(new NestedSameFieldname());
    }

    /*  SSplit  */

    @Test
    public void basic08_DataSuiteSSplit_Goessener() {
        forDataSuiteSSplit(new Goessener());
    }

    @Test
    public void basic09_DataSuiteSSplit_JsonArray() {
        forDataSuiteSSplit(new JsonArray());
    }

    @Test
    public void basic10_DataSuiteSSplit_JsonArrayMulti() {
        forDataSuiteSSplit(new JsonArrayMulti());
    }

    @Test
    public void basic11_DataSuiteSSplit_JsonPathWebsite() {
        forDataSuiteSSplit(new JsonPathWebsite());
    }

    @Test
    public void basic12_DataSuiteSSplit_NestedFieldname() {
        forDataSuiteSSplit(new NestedFieldname());
    }

    @Test
    public void basic13_DataSuiteSSplit_NestedSameFieldname() {
        forDataSuiteSSplit(new NestedSameFieldname());
    }

    @Test
    public void basic14_DataSuiteSplit_XMarkSample() {
        forDataSuiteSplit(new XMarkSample());
    }
}