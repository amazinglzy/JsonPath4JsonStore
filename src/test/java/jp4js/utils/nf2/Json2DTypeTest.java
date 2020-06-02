package jp4js.utils.nf2;

import org.junit.Test;

import jp4js.nf2.rel.DType;
import jp4js.utils.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.LinkedList;

public class Json2DTypeTest {
    @Test
    public void basic01_() {
        String json1 = "{\"name\": \"Alice\"}";
        String json2 = "{\"name\": [\"Alice\", \"Bob\"]}";
        String json3 = "{\"name\": 10.2}";
        String json4 = "{\"name\": 1}";

        Configuration configuration = Configuration.defaultConfiguration();
        List<Object> jsonLst = new LinkedList<>() {{
            add(configuration.jsonProvider().parse(json1));
            add(configuration.jsonProvider().parse(json2));
            add(configuration.jsonProvider().parse(json3));
            add(configuration.jsonProvider().parse(json4));
        }};

        DType relation = new Json2DType(jsonLst, configuration).relation();
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
        List<Object> jsonLst = new LinkedList<>() {{
            add(configuration.jsonProvider().parse(json1));
            add(configuration.jsonProvider().parse(json2));
            add(configuration.jsonProvider().parse(json3));
        }};

        DType relation = new Json2DType(jsonLst, configuration).relation();
        assertThat(relation.toString()).isEqualTo(
            "[int](DInt), [str](DString)"
        );
    }
}