package jp4js.utils.query;

import org.junit.Test;

import jp4js.nf2.op.structure.StructureList;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        String p1 = "$.store.book[*].author";
        String p2 = "$..author";
        String p3 = "$.store.*";
        String p4 = "$.store..price";
        String p5 = "$..book[2]";

        StructureList lst1 = PathCompiler.fromString(p1);
        StructureList lst2 = PathCompiler.fromString(p2);
        StructureList lst3 = PathCompiler.fromString(p3);
        StructureList lst4 = PathCompiler.fromString(p4);
        StructureList lst5 = PathCompiler.fromString(p5);

        // System.out.println(lst1.toString());
        // System.out.println(lst2.toString());
        // System.out.println(lst3.toString());
        // System.out.println(lst4.toString());
        // System.out.println(lst5.toString());

        assertThat(lst1.toString()).isEqualTo(
            "(.store(.book[.author]))"
        );
        assertThat(lst2.toString()).isEqualTo(
            "(..author)"
        );
        assertThat(lst3.toString()).isEqualTo(
            "(.store(.*))"
        );
        assertThat(lst4.toString()).isEqualTo(
            "(.store(..price))"
        );
        assertThat(lst5.toString()).isEqualTo(
            "(..book[])"
        );

    }
}