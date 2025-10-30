package lotto.domain.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.common.constants.Rank;
import lotto.infrastructure.RandomNumberMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchasedLottosTest {

    @Test
    void 로또_수량에_따른_구매로또_생성() {
        assertThat(
                PurchasedLottos.create(
                        3,
                        new RandomNumberMaker())
        ).isNotNull();
    }

    @DisplayName("매칭 결과를 EnumMap<Rank, Integer> 형태로 반환한다.")
    @Test
    void 사용자_로또와_구매_로도_매칭_결과_반환() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    UserLotto userLotto = UserLotto.create(
                            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                            7
                    );
                    PurchasedLottos purchasedLottos = PurchasedLottos.create(
                            3,
                            new RandomNumberMaker()
                    );

                    Map<Rank, Integer> actual = purchasedLottos.calculateWinningLotto(userLotto);
                    Map<Rank, Integer> expected = new HashMap<>(Map.of(
                            Rank.SECOND, 1,
                            Rank.FIFTH, 2
                    ));

                    assertThat(actual).isEqualTo(expected);
                },
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 7, 41, 42),
                List.of(4, 5, 6, 41, 43, 44)
        );
    }

}
