package jp4js.nf2;

public class NestedRelationSample {
    public static NestedRelation flatRel0, nestedRel0;
    static {
        flatRel0 = new NestedRelationBuilder()
            .put("age", BasicType.dInt)
            .put("name", BasicType.dString)
            .build();
        nestedRel0 = new NestedRelationBuilder()
            .put("age",BasicType.dInt)
            .put("name", BasicType.dString)
            .enter()
                .put("course", BasicType.dString)
                .put("score", BasicType.dInt)
            .exit("courses")
            .build();
    }
}