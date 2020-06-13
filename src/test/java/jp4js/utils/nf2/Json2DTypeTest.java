package jp4js.utils.nf2;

import org.junit.Test;

import jp4js.nf2.rel.DType;
import jp4js.nf2.rel.NestedRelationBuilder;
import jp4js.storage.MemStore;
import jp4js.utils.Configuration;

import static org.assertj.core.api.Assertions.assertThat;


public class Json2DTypeTest {
    @Test
    public void basic01_() {
        String json1 = "{\"name\": \"Alice\"}";
        String json2 = "{\"name\": [\"Alice\", \"Bob\"]}";
        String json3 = "{\"name\": 10.2}";
        String json4 = "{\"name\": 1}";

        Configuration configuration = Configuration.defaultConfiguration();
        MemStore store = new MemStore() {{
            add(configuration.jsonProvider().parse(json1), configuration);
            add(configuration.jsonProvider().parse(json2), configuration);
            add(configuration.jsonProvider().parse(json3), configuration);
            add(configuration.jsonProvider().parse(json4), configuration);
        }};

        NestedRelationBuilder builder = new NestedRelationBuilder();
        new Json2DType(store.docs(), "", builder);
        DType relation = builder.build();
        assertThat(relation.toString()).isEqualTo(
            "name([str](DString)), name.[double](DDouble), name.[int](DInt), name.[str](DString)"
        );
    }

    @Test
    public void basic02_() {
        String json1 = "\"Good\"";
        String json2 = "\"Nice\"";
        String json3 = "34";

        Configuration configuration = Configuration.defaultConfiguration();
        MemStore store = new MemStore() {{
            add(configuration.jsonProvider().parse(json1), configuration);
            add(configuration.jsonProvider().parse(json2), configuration);
            add(configuration.jsonProvider().parse(json3), configuration);
        }};

        NestedRelationBuilder builder = new NestedRelationBuilder();
        new Json2DType(store.docs(), "", builder);
        DType relation = builder.build();
        assertThat(relation.toString()).isEqualTo(
            "[int](DInt), [str](DString)"
        );
    }
}