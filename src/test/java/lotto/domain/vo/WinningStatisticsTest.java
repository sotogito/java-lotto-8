package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import lotto.common.constants.Rank;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    void 최종_상금_계산() {
        WinningStatistics winningStatistics = new WinningStatistics(
                Map.of(
                        Rank.FIFTH, 2,
                        Rank.NOTHING, 5
                )
        );

        long actual = winningStatistics.calculateTotalPrize();
        long expected = 10_000L;

        assertThat(actual).isEqualTo(expected);
    }

}
