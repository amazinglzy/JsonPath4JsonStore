package jp4js.query.scan;

import jp4js.query.RecordSet;
import jp4js.query.RecordSet.Record;
import jp4js.utils.Configuration;
import jp4js.index.node.ArrayNode.ArraySlice;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.LinkedList;

public class RecordGeneratorTest {
    private static Object basic01;
    private static Configuration configuration = Configuration.defaultConfiguration();

    static {
        String str = "{\n" +
            "\"field1\": {\n" +
                "\"field1\": [3]" +
            "}," +
            "\"field2\": { " +
                "\"field1\": \"field2-field1\"" +
            "}, "+
            "\"field3\": [1, 2, 3]"+
        "}";
        basic01 = configuration.jsonProvider().parse(str);
    }

    @Test
    public void testStepProperties01() {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", basic01);

        RecordGenerator recordGenerator = new RecordGenerator(recordSet, configuration);
        recordGenerator.step(new LinkedList<>(){{
            add("field2");
        }});
        recordGenerator.step(new LinkedList<>(){{
            add("field1");
        }});

        Iterator<Record> iter = recordGenerator.data().iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("field2-field1");
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testStepArraySelections01() {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", basic01);

        RecordGenerator recordGenerator = new RecordGenerator(recordSet, configuration);
        recordGenerator.step(new LinkedList<>(){{
            add("field3");
        }});
        recordGenerator.step(new ArraySlice(0, 2));
        Iterator<Record> iter = recordGenerator.data().iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(1);
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(2);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testStepWildcard01() {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", basic01);

        RecordGenerator recordGenerator = new RecordGenerator(recordSet, configuration);
        recordGenerator.stepWildcard();
        recordGenerator.stepWildcard();
        recordGenerator.stepWildcard();
        
        Iterator<Record> iter = recordGenerator.data().iterator();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo(3);
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    public void testStepScan01() {
        RecordSet recordSet = new RecordSet(configuration);
        recordSet.append("$", basic01);


        RecordGenerator recordGenerator = new RecordGenerator(recordSet, configuration);
        recordGenerator.stepScan(new LinkedList<>(){{
            add("field1");
        }});
        Iterator<Record> iter = recordGenerator.data().iterator();
        assertThat(iter.hasNext()).isTrue(); iter.next();
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue().toString()).isEqualTo("[3]");
        assertThat(iter.hasNext()).isTrue(); assertThat(iter.next().getValue()).isEqualTo("field2-field1");
        assertThat(iter.hasNext()).isFalse();
    }
}