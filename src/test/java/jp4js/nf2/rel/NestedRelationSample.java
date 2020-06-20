package jp4js.nf2.rel;

public class NestedRelationSample {
    public static NestedRelation flatRel0, nestedRel0, nestedRel1;
    static {
        flatRel0 = new NestedRelationBuilder()
            .put("age", Document.dInt)
            .put("name", Document.dString)
            .build();
        nestedRel0 = new NestedRelationBuilder()
            .put("age",Document.dInt)
            .put("name", Document.dString)
            .enter("courses")
                .put("course", Document.dString)
                .put("score", Document.dInt)
            .exit()
            .build();
        nestedRel1 = new NestedRelationBuilder()
            .enter("customer")
                .put("name.first.[str]", Document.dString)
                .put("name.last.[str]", Document.dString)
                .enter("address")
                    .put("[str]", Document.dString)
                .exit()
            .exit()
            .build();
    }
}