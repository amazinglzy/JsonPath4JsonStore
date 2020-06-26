package jp4js.nf2.tpl;

import jp4js.nf2.Scalar;

public class NestedRelationSample {
    public static DHeader flatRel0, nestedRel0, nestedRel1;
    static {
        /*
        (
            name(
                first(DString), 
                last(DString)
            ), 
            addr[
                country(DString), 
                province(DString)
            ],
            scores[DString]
        )
        put(DType type)
        put(String field, DType type)
        put(String field, DType type, TemplateType type)
        enter(String field)
        enter(String field, TemplateType type)
        exit()

        */
        flatRel0 = new DHeaderBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .build();
        nestedRel0 = new DHeaderBuilder()
            .put("age",Scalar.dInt)
            .put("name", Scalar.dString)
            .enter("courses")
                .put("course", Scalar.dString)
                .put("score", Scalar.dInt)
            .exit()
            .build();
        nestedRel1 = new DHeaderBuilder()
            .enter("customer")
                .put("name.first.[str]", Scalar.dString)
                .put("name.last.[str]", Scalar.dString)
                .enter("address")
                    .put("[str]", Scalar.dString)
                .exit()
            .exit()
            .build();
    }
}