package lotto.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class UserLottoTest {

    @Test
    void 보너스번호_일치_여부() {
        UserLotto userLotto = UserLotto.create(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                7
        );
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        boolean actual = userLotto.hasBonusNumberFrom(purchasedLotto);

        assertThat(actual).isTrue();
    }

    @Test
    void 보너스_번호_6자리_로또와_중복일_경우_예외처리() {
        Lotto mainLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> UserLotto.create(mainLotto, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
