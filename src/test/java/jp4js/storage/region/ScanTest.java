package jp4js.storage.region;

import jp4js.data.BaseDataSuite;
import jp4js.data.Goessener;
import jp4js.data.JsonArray;
import jp4js.data.JsonArrayMulti;
import jp4js.data.JsonPathWebsite;
import jp4js.data.NestedFieldname;
import jp4js.data.NestedSameFieldname;
import jp4js.data.XMarkSample;
import jp4js.algebra.Match;
import jp4js.algebra.op.FullScan;
import jp4js.algebra.op.structure.StructureList;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ScanTest {
    public void forDataSuiteSSplit(BaseDataSuite suite) {
        IndexContext indexContext = Indexer.index(suite.instance());
        for (int i = 0; i < suite.querySize(); i++) {
            StructureList lst = suite.query(i);
            RegionScan split = new RegionScan(indexContext, lst);
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
            } catch (FullScan.MatchException e) {
                e.printStackTrace();
            }
        }
    }

    /*  SSplit  */

    @Test
    public void basic01_DataSuiteSSplit_Goessener() {
        forDataSuiteSSplit(new Goessener());
    }

    @Test
    public void basic02_DataSuiteSSplit_JsonArray() {
        forDataSuiteSSplit(new JsonArray());
    }

    @Test
    public void basic3_DataSuiteSSplit_JsonArrayMulti() {
        forDataSuiteSSplit(new JsonArrayMulti());
    }

    @Test
    public void basic4_DataSuiteSSplit_JsonPathWebsite() {
        forDataSuiteSSplit(new JsonPathWebsite());
    }

    @Test
    public void basic5_DataSuiteSSplit_NestedFieldname() {
        forDataSuiteSSplit(new NestedFieldname());
    }

    @Test
    public void basic6_DataSuiteSSplit_NestedSameFieldname() {
        forDataSuiteSSplit(new NestedSameFieldname());
    }

    @Test
    public void basic7_DataSuiteSSplit_XMarkSample() {
        forDataSuiteSSplit(new XMarkSample());
    }
}