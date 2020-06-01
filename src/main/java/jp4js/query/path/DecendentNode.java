package jp4js.query.path;

import java.util.List;
import java.util.Collection;

public class DecendentNode extends PathNode {
    private List<String> properties;

    public DecendentNode(List<String> properties) {
        this.properties = properties;
    }

    public Collection<String> properties() {
        return this.properties;
    }

    @Override
    public String toString() {
        String ret = "";
        for (String property: this.properties) {
            if (ret.length() != 0) ret += ", ";
            ret += property;
        }
        ret += super.toString();
        return ret;
    }
}