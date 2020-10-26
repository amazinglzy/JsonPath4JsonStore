package jp4js;

import java.util.List;
import jp4js.algebra.tpl.NestedData;

public interface JsonPath {
    public void index(String json);
    public List<NestedData> query(String query);
}