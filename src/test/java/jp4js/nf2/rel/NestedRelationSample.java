package jp4js.nf2.rel;

public class NestedRelationSample {
    public static NestedRelation flatRel0, nestedRel0, nestedRel1;
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
        nestedRel1 = new NestedRelationBuilder()
            .enter()
                .put("name.first.[str]", BasicType.dString)
                .put("name.last.[str]", BasicType.dString)
                .enter()
                    .put("[str]", BasicType.dString)
                .exit("address")
            .exit("customer")
            .build();
    }
}