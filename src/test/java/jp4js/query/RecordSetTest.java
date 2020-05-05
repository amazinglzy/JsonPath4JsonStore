package jp4js.query;

import jp4js.utils.Configuration;
import jp4js.utils.iter.Iter;
import jp4js.query.RecordSet.Record;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RecordSetTest {

    @Test
    public void testRecordSetIter() {
        RecordSet set = new RecordSet(Configuration.defaultConfiguration());
        set.append("path1", 1);
        set.append("path2", 2);
        set.append("path3", 3);
        set.append("path4", 4);
        Iter<Record> iter = set.iterator();
        assertThat(iter.hasNext()); assertThat(iter.read().getPath()).isEqualTo("path1"); iter.next();
        assertThat(iter.hasNext()); assertThat(iter.read().getPath()).isEqualTo("path2"); iter.next();
        assertThat(iter.hasNext()); assertThat(iter.read().getPath()).isEqualTo("path3"); iter.next();
        assertThat(iter.hasNext()); assertThat(iter.read().getPath()).isEqualTo("path4"); iter.next();
    }
}