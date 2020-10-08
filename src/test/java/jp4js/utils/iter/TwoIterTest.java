package jp4js.utils.iter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TwoIterTest {
    @Test
    public void basic01_() {
        Iter<Integer> iter1 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(1, 4, 7)
        ));
        Iter<Integer> iter2 = new ArrayIter<Integer>(new ArrayList<Integer>(
            Arrays.asList(2, 3, 6)
        ));

        Iter<Integer> iter = new TwoIter<>(iter1, iter2, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });


        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(1); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(2); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(3); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(4); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(6); iter.next();
        assertThat(iter.valid()).isTrue(); assertThat(iter.read()).isEqualTo(7); iter.next();
        assertThat(iter.valid()).isFalse();
    }
}
