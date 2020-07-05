package jp4js.data;

import jp4js.nf2.DType;
import jp4js.nf2.op.structure.StructureList;
import jp4js.utils.Configuration;
import jp4js.utils.nf2.Trans;
import jp4js.utils.query.PathCompiler;

public abstract class BaseDataSuite {
    public abstract String data();
    public abstract String[] querySet();

    public Configuration configuration() {
        return Configuration.defaultConfiguration();
    }

    public Object json() {
        return configuration().jsonProvider().parse(data());
    }

    public DType.Instance instance() {
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