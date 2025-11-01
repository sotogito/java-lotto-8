package lotto.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

    @Test
    void notNull() {
        assertThat(
                NumberValidator.notNull(1))
                .isTrue();
    }

    @Test
    void inRange() {
        assertThat(
                NumberValidator.inRange(3, 1, 5))
                .isTrue();
    }

    @Test
    void isDivisibleBy() {
        assertThat(
                NumberValidator.isDivisibleBy(3000, 1000))
                .isTrue();
    }

    @Test
    void allNotNull() {
        assertThat(
                NumberValidator.allNotNull(List.of(1, 2, 3, 4, 5, 6)))
                .isTrue();
    }

    @Test
    void allInRange() {
        assertThat(
                NumberValidator.allInRange(List.of(1, 2, 3, 4, 5, 6), 1, 45))
                .isTrue();
    }

    @Test
    void hasExactSize() {
        assertThat(
                NumberValidator.hasExactSize(List.of(1, 2, 3, 4, 5, 6), 6))
                .isTrue();
    }

    @Test
    void hasDuplicate() {
        assertThat(
                NumberValidator.hasDuplicate(List.of(1, 2, 3, 4, 5, 6)))
                .isTrue();
    }

}
