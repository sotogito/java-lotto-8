package lotto.domain.vo;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import lotto.common.constants.Rank;

public class WinningStatistics {
    private final Map<Rank, Integer> winningStatistics;

    public WinningStatistics(Map<Rank, Integer> winningStatistics) {
        this.winningStatistics = winningStatistics;
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;

        for (Map.Entry<Rank, Integer> entry : winningStatistics.entrySet()) {
            Rank rank = entry.getKey();
            long prize = rank.getPrizeMoney();
            long count = entry.getValue();

            totalPrize += (prize * count);
        }
        return totalPrize;
    }

    public Map<Rank, Integer> getWinningStatisticsWithAllRank() {
        EnumMap<Rank, Integer> winningStatisticsWithAllRank = Rank.initRankByCount();
        winningStatisticsWithAllRank.remove(Rank.NOTHING);

        for (Map.Entry<Rank, Integer> entry : winningStatistics.entrySet()) {
            Rank rank = entry.getKey();
            Integer count = entry.getValue();

            if (winningStatisticsWithAllRank.containsKey(rank)) {
                winningStatisticsWithAllRank.put(rank, count);
            }
        }
        return Collections.unmodifiableMap(winningStatisticsWithAllRank);
    }

}
