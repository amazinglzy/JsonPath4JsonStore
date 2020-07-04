package jp4js.query;

import jp4js.nf2.op.structure.StructureList;
import jp4js.utils.query.PathCompiler;

public class JsonPathSample {
    public static final StructureList lst1 = PathCompiler.fromString("$.store.book[*].author");
    public static final StructureList lst2 = PathCompiler.fromString("$..author");
    public static final StructureList lst3 = PathCompiler.fromString("$.store.*");
    public static final StructureList lst4 = PathCompiler.fromString("$.store..price");
    public static final StructureList lst5 = PathCompiler.fromString("$..book[2]");
}