package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 구매_로또_수량_계상() {
        Money money = new Money(1000);

        int actual = money.calculatePurchasedQuantity();
        int expected = 1;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 수익률_계산() {
        Money money = new Money(5000);
        long totalPrice = 5000L;

        BigDecimal actual = money.calculateYield(totalPrice);
        BigDecimal expected = BigDecimal.valueOf(100.0);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("구매 금액은 1,000단위로 입력할 수 있다.")
    @Test
    void 구매_굼액_단위_예외처리() {
        assertThatThrownBy(() -> new Money(11111))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또는 1000장까지 구매할 수 있다.")
    @Test
    void 구매_로또_수량_예외처리() {
        assertThatThrownBy(() -> new Money(10000000))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
