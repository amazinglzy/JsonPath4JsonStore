package jp4js;

import java.util.List;
import jp4js.algebra.tpl.DBody;

public interface JsonPath {
    public void index(String json);
    public List<DBody> query(String query);
}