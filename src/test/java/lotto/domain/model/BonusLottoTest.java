package lotto.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusLottoTest {

    @DisplayName("로또 번호는 1~45범위로 이루어져야 한다.")
    @Test
    void 보너스_로또_범위_예외처리() {
        assertThatThrownBy(() -> new BonusLotto(100))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
