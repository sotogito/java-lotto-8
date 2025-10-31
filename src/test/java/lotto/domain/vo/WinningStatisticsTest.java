package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import lotto.common.constants.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @DisplayName("매칭된 Rank와 모든 Rank를 반환한다. 단, NOTHING은 반환하지 않는다.")
    @Test
    void 모든_랭킹과_매칭_랭킹_결과_반환() {
        WinningStatistics winningStatistics = new WinningStatistics(
                Map.of(
                        Rank.FIFTH, 2,
                        Rank.NOTHING, 5
                )
        );

        Map<Rank, Integer> actual = winningStatistics.getWinningStatisticsWithAllRank();

        assertThat(actual)
                .containsEntry(Rank.FIFTH, 2)
                .containsEntry(Rank.FOURTH, 0)
                .containsEntry(Rank.THIRD, 0)
                .containsEntry(Rank.SECOND, 0)
                .containsEntry(Rank.FIRST, 0)
                .doesNotContainEntry(Rank.NOTHING, 5);
    }

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
