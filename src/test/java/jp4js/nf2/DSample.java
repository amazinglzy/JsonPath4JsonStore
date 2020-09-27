package jp4js.nf2;

import java.util.LinkedList;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DHeader;
import jp4js.nf2.tpl.DHeaderBuilder;
import jp4js.nf2.tpl.ListTuple;
import jp4js.nf2.tpl.Tuple;

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
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .build();
        flatRel0_bodys = new DBody[] {
            new ListTuple(new LinkedList<DBody>() {{
                add(new Tuple(new DBody[] {
                    Scalar.createAtomicInt(20),
                    Scalar.createAtomicString("Alice")
                }));
                add(new Tuple(new DBody[] {
                    Scalar.createAtomicInt(22),
                    Scalar.createAtomicString("Bob")
                }));
            }}),
            new ListTuple(new LinkedList<DBody>() {{
                add(new Tuple(new DBody[] {
                    Scalar.createAtomicInt(30),
                    Scalar.createAtomicString("Cisar")
                }));
                add(new Tuple(new DBody[] {
                    Scalar.createAtomicInt(32),
                    Scalar.createAtomicString("Dense")
                }));
            }})
        };
        
        nestedRel0 = new DHeaderBuilder()
            .put("age", Scalar.dInt)
            .put("name", Scalar.dString)
            .enter("courses", 1)
                .put("course", Scalar.dString)
                .put("score", Scalar.dInt)
            .exit()
            .build();
        nestedRel0_bodys = new DBody[] {
            new ListTuple(new LinkedList<DBody>(){{
                add(new Tuple(new DBody[] {
                    Scalar.createAtomicInt(43),
                    Scalar.createAtomicString("Alice"),
                    new ListTuple(new LinkedList<>() {{
                        add(new Tuple(new DBody[] {
                            Scalar.createAtomicString("English"),
                            Scalar.createAtomicInt(80)
                        }));
                        add(new Tuple(new DBody[] {
                            Scalar.createAtomicString("Chinese"),
                            Scalar.createAtomicInt(90)
                        }));
                    }})
                }));
            }})
        };

        nestedRel1 = new DHeaderBuilder()
            .enter("customer", 1)
                .put("name.first.[str]", Scalar.dString)
                .put("name.last.[str]", Scalar.dString)
                .enter("address", 1)
                    .put("[str]", Scalar.dString)
                .exit()
            .exit()
            .build();
    }
}