package jp4js.benchmark.adapter;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.TplValidator;
import jp4js.algebra.operator.structure.StructureList;
import jp4js.storage.region.IndexContext;
import jp4js.storage.region.Indexer;
import jp4js.storage.region.RegionScan;

public class RegionScanAdatper implements TplAdapter {
    private IndexContext indexContext;

    public RegionScanAdatper() {
        
    }

    @Override
    public void index(Object json, Configuration configuration) {
        this.indexContext = Indexer.index(json, configuration);
    }

    @Override
    public int query(StructureList lst) {
        RegionScan scan = new RegionScan(this.indexContext, lst);
        try {
            TplValidator match = scan.open();
            // assert(match.isValid());
            return match.body().size();
        } catch (RegionScan.MatchException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
