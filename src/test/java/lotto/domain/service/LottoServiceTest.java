package lotto.domain.service;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.common.constants.Rank;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;
import lotto.infrastructure.RandomNumberMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService(new RandomNumberMaker());
    }

    @DisplayName("사용자가 입력한 금액으로 구입한 로또를 반환한다.")
    @Test
    void 로또_구매() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    Money money = new Money(1000);

                    PurchasedLottos actual = lottoService.purchase(money);

                    assertThat(actual.toString())
                            .contains("[1, 2, 3, 4, 5, 6]");
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    void 로또_당첨_랭킹_통계_반환() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    UserLotto userLotto = UserLotto.create(
                            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                            7
                    );
                    PurchasedLottos purchasedLottos = lottoService.purchase(new Money(1000));

                    WinningStatistics winningStatistics =
                            lottoService.getWinningStatistics(userLotto, purchasedLottos);
                    Map<Rank, Integer> actual = winningStatistics.getWinningStatisticsWithAllRank();

                    assertThat(actual).containsEntry(Rank.FIRST, 1);
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    void 수릭률_계산() {
        Money money = new Money(3000);
        WinningStatistics winningStatistics = new WinningStatistics(
                Map.of(
                        Rank.FIRST, 1,
                        Rank.SECOND, 2
                )
        );

        BigDecimal actual = lottoService.calculateYield(money, winningStatistics);
        BigDecimal expected = BigDecimal.valueOf(68666666.7);

        assertThat(actual).isEqualTo(expected);
    }

}
