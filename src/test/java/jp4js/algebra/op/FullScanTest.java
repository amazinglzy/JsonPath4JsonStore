package jp4js.algebra.op;

import jp4js.algebra.DType;
import jp4js.algebra.Match;
import jp4js.algebra.op.structure.StructureList;
import jp4js.data.BaseDataSuite;
import jp4js.data.Goessener;
import jp4js.data.JsonArray;
import jp4js.data.JsonArrayMulti;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.data.XMarkSample;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

public class FullScanTest {

    public void forDataSuiteSplit(BaseDataSuite suite) {
        for (int i = 0; i < suite.querySize(); i++) {
            StructureList lst = suite.query(i);
            FullScan split = new FullScan(suite.instance(), lst);
            try {
                Match match = split.open();
                match.match();
                assertThat(match.isValid()).isTrue();
                System.out.println(match.header().toString());
                System.out.println(match.body().toString());
                if (i < suite.res().length) {
                    assertThat(match.body().toString()).isEqualTo(
                        suite.res()[i]
                    );
                }
            } catch (FullScan.MatchException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void basic01_() {
        DType.Instance instance = new Goessener().instance();

        FullScan split = new FullScan(instance, new Goessener().query(0));
        try {
            Match match = split.open();
            match.match();
            assertThat(match.isValid()).isTrue();
            System.out.println(match.header().toString());
            System.out.println(match.body().toString());
            assertThat(match.header().toString()).isEqualTo(
                "[(.store.book.*.author)]"
            );
            assertThat(match.body().toString()).isEqualTo(
                "[[(\"Nigel Rees\"), (\"Evelyn Waugh\"), (\"Herman Melville\"), (\"J. R. R. Tolkien\")]]"
            );
        } catch (FullScan.MatchException e) {
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

    @Test
    public void basic08_DataSuiteSplit_XMarkSample() {
        forDataSuiteSplit(new XMarkSample());
    }
}
