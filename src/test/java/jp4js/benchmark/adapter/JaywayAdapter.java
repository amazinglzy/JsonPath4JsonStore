package jp4js.benchmark.adapter;

import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.cache.Cache;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import com.jayway.jsonpath.spi.cache.NOOPCache;

public class JaywayAdapter implements JsonPathAdapter {
    private ReadContext context;

    public JaywayAdapter() {
        Cache cache = new NOOPCache();
        CacheProvider.setCache(cache);
    }

    @Override
    public void index(Object json, Configuration configuration) {
        this.context = JsonPath.parse(json, configuration);
    }

    @Override
    public int query(String query) {
        List<?> r = this.context.read(query);
        return r.size();
    }
}
