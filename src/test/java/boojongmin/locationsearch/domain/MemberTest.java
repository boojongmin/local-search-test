package boojongmin.locationsearch.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberTest {
    @Test
    public void testNonNull() {
        assertThatThrownBy(() -> {
            Member.builder().build();
        }).isInstanceOf(NullPointerException.class);
    }
}