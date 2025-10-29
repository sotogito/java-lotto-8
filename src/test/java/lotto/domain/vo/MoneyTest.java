package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("구매 금액은 1,000단위로 입력할 수 있다.")
    @Test
    void 구매_굼액_단위_예외처리() {
        assertThatThrownBy(() -> new Money(11111))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또는 1000장까지 구매할 수 있다..")
    @Test
    void 구매_로또_수량_예외처리() {
        assertThatThrownBy(() -> new Money(10000000))
                .isInstanceOf(IllegalArgumentException.class);
    }

}