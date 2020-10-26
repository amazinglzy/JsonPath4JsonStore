package jp4js.data;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.Domain;
import jp4js.algebra.operator.structure.StructureList;
import jp4js.utils.algebra.Trans;
import jp4js.utils.query.PathCompiler;

public abstract class BaseDataSuite {
    public abstract String data();
    public abstract String[] querySet();
    public abstract String[] res();

    public Configuration configuration() {
        return Configuration.defaultConfiguration();
    }

    public Object json() {
        return configuration().jsonProvider().parse(data());
    }

    public Domain.Instance instance() {
        return Trans.fromJSON(
            configuration().jsonProvider().parse(data()), 
            configuration());
    }

    public int querySize() {
        return querySet().length;
    }

    public StructureList query(int i) {
        return PathCompiler.fromString(querySet()[i]);
    }
}