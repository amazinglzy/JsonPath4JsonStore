package jp4js.algebra;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {
    @Test
    public void basic01_() {
        TplValidator match = new TplValidator(
            DSample.flatRel0,
            DSample.flatRel0_bodys[0]
        );
        match.match();
        assertThat(match.isValid()).isTrue();
    }

    @Test
    public void basic02_() {
        TplValidator match0 = new TplValidator(
            DSample.nestedRel0,
            DSample.nestedRel0_bodys[0]
        );
        match0.match();
        assertThat(match0.isValid()).isTrue();
    }
}