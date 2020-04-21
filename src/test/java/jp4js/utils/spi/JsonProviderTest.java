package jp4js.utils.spi;

import org.junit.Test;

import jp4js.utils.Configuration;
import jp4js.utils.spi.json.JsonProvider;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonProviderTest {

    @Test
    public void testGetGsonEmptyProperty() {
        Configuration configuration = Configuration.defaultConfiguration();
        String str = "{\"a\": 1}";
        Object jsonObject = configuration.jsonProvider().parse(str);
        Object valueOfNotExistsKey = configuration.jsonProvider().getMapValue(jsonObject, "b");
        assertThat(valueOfNotExistsKey).isEqualTo(JsonProvider.UNDEFINED);
    }

    @Test
    public void testGetGsonEmptyArrayIndex() {
        Configuration configuration = Configuration.defaultConfiguration();
        String str = "[1, 2, 3]";
        Object jsonObject = configuration.jsonProvider().parse(str);
        Object valueOfNotExistsIndex = configuration.jsonProvider().getArrayIndex(jsonObject, 3);
        assertThat(valueOfNotExistsIndex).isEqualTo(JsonProvider.UNDEFINED);
    }
}