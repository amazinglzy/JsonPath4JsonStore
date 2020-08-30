package jp4js;

import jp4js.nf2.tpl.DBody;

public interface JsonPath {
    public void index(String json);
    public DBody query(String query);
}