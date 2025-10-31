package lotto.domain.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.common.constants.Rank;
import lotto.domain.vo.WinningStatistics;
import lotto.infrastructure.RandomNumberMaker;
import org.junit.jupiter.api.Test;

class PurchasedLottosTest {

    @Test
    void 로또_수량에_따른_구매로또_생성() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    PurchasedLottos purchasedLottos = PurchasedLottos.create(
                            2,
                            new RandomNumberMaker()
                    );

                    assertThat(purchasedLottos.toString())
                            .contains("[1, 2, 3, 4, 5, 6]"
                                    , "[6, 7, 8, 9, 10, 11]");
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(6, 7, 8, 9, 10, 11)
        );
    }

    @Test
    void 사용자_로또와_구매_로또_매칭_결과_반환() {
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

                    WinningStatistics winningStatistics = purchasedLottos.calculateWinningLotto(userLotto);
                    Map<Rank, Integer> actual = winningStatistics.getWinningStatisticsWithAllRank();

                    assertThat(actual)
                            .containsEntry(Rank.FIFTH, 2)
                            .containsEntry(Rank.SECOND, 1);
                },
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 7, 41, 42),
                List.of(4, 5, 6, 41, 43, 44)
        );
    }

}
