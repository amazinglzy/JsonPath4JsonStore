package jp4js.nf2;

import java.util.LinkedList;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DHeaderBuilder;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;

public class DSample {
    public static final DHeader flatRel0, nestedRel0, nestedRel1;
    public static final DBody[] flatRel0_bodys, nestedRel0_bodys;
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
            .put("age")
            .put("name")
            .build();
        flatRel0_bodys = new DBody[] {
            new DRepeatableBody(new LinkedList<DBody>() {{
                add(new DSingularBody(new DBody[] {
                    new DSingularBody(Scalar.createDInt(20)),
                    new DSingularBody(Scalar.createDString("Alice"))
                }));
                add(new DSingularBody(new DBody[] {
                    new DSingularBody(Scalar.createDInt(22)),
                    new DSingularBody(Scalar.createDString("Bob"))
                }));
            }}),
            new DRepeatableBody(new LinkedList<DBody>() {{
                add(new DSingularBody(new DBody[] {
                    new DSingularBody(Scalar.createDInt(30)),
                    new DSingularBody(Scalar.createDString("Cisar"))
                }));
                add(new DSingularBody(new DBody[] {
                    new DSingularBody(Scalar.createDInt(32)),
                    new DSingularBody(Scalar.createDString("Dense"))
                }));
            }})
        };
        
        nestedRel0 = new DHeaderBuilder()
            .put("age")
            .put("name")
            .enter("courses")
                .put("course")
                .put("score")
            .exit()
            .build();
        nestedRel0_bodys = new DBody[] {
            new DRepeatableBody(new LinkedList<DBody>(){{
                add(new DSingularBody(new DBody[] {
                    new DSingularBody(Scalar.createDInt(43)),
                    new DSingularBody(Scalar.createDString("Alice")),
                    new DRepeatableBody(new LinkedList<>() {{
                        add(new DSingularBody(new DBody[] {
                            new DSingularBody(Scalar.createDString("English")),
                            new DSingularBody(Scalar.createDInt(80))
                        }));
                        add(new DSingularBody(new DBody[] {
                            new DSingularBody(Scalar.createDString("Chinese")),
                            new DSingularBody(Scalar.createDInt(90))
                        }));
                    }})
                }));
            }})
        };

        nestedRel1 = new DHeaderBuilder()
            .enter("customer")
                .put("name.first.[str]")
                .put("name.last.[str]")
                .enter("address")
                    .put("[str]")
                .exit()
            .exit()
            .build();
    }
}