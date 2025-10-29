package lotto.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class UserLottoTest {

    @Test
    void 보너스_번호_6자리_로또와_중복일_경우_예외처리() {
        Lotto mainLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> UserLotto.createUserLotto(mainLotto, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
