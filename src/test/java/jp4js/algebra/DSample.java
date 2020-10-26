package jp4js.algebra;

import java.util.LinkedList;

import jp4js.algebra.tpl.NestedData;
import jp4js.algebra.tpl.DHeader;
import jp4js.algebra.tpl.DHeaderBuilder;
import jp4js.algebra.tpl.ListTuple;
import jp4js.algebra.tpl.Tuple;

public class DSample {
    public static final DHeader flatRel0, nestedRel0, nestedRel1;
    public static final NestedData[] flatRel0_bodys, nestedRel0_bodys;
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
        flatRel0_bodys = new NestedData[] {
            new ListTuple(new LinkedList<NestedData>() {{
                add(new Tuple(new NestedData[] {
                    Scalar.createAtomicInt(20),
                    Scalar.createAtomicString("Alice")
                }));
                add(new Tuple(new NestedData[] {
                    Scalar.createAtomicInt(22),
                    Scalar.createAtomicString("Bob")
                }));
            }}),
            new ListTuple(new LinkedList<NestedData>() {{
                add(new Tuple(new NestedData[] {
                    Scalar.createAtomicInt(30),
                    Scalar.createAtomicString("Cisar")
                }));
                add(new Tuple(new NestedData[] {
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
        nestedRel0_bodys = new NestedData[] {
            new ListTuple(new LinkedList<NestedData>(){{
                add(new Tuple(new NestedData[] {
                    Scalar.createAtomicInt(43),
                    Scalar.createAtomicString("Alice"),
                    new ListTuple(new LinkedList<>() {{
                        add(new Tuple(new NestedData[] {
                            Scalar.createAtomicString("English"),
                            Scalar.createAtomicInt(80)
                        }));
                        add(new Tuple(new NestedData[] {
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