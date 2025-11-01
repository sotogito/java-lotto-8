package lotto.ui;

import java.math.BigDecimal;
import java.util.Map;
import java.util.StringJoiner;
import lotto.common.constants.Rank;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;

public class OutputView {
    private final static String WRITE_PURCHASE_LOTTOS_FORMAT = "%,d개를 구매했습니다.\n";
    private final static String WRITE_MATCHING_RANK_FORMAT = "%,d개 일치 (%,d원) - %,d개";
    private final static String WRITE_MATCHING_RANK_WITH_BONUS_FORMAT = "%,d개 일치, 보너스 볼 일치 (%,d원) - %,d개";
    private final static String WRITE_WINNING_STATISTICS_HEAD = "당첨 통계\n---\n";
    private final static String WRITE_YIELD_FORMAT = "총 수익률은 %,.1f%%입니다.";

    public static void writePurchaseLottos(Money money, PurchasedLottos purchasedLottos) {
        System.out.println();
        System.out.printf(WRITE_PURCHASE_LOTTOS_FORMAT, money.calculatePurchasedQuantity());
        System.out.println(purchasedLottos.toString());
        System.out.println();
    }

    public static void writeWinningStatistics(WinningStatistics winningStatistics) {
        StringJoiner result = new StringJoiner("\n");

        for (Map.Entry<Rank, Integer> entry :
                winningStatistics.getWinningStatisticsWithAllRank().entrySet()) {
            Rank rank = entry.getKey();
            Integer count = entry.getValue();

            result.add(getRankPrintout(rank, count));
        }
        System.out.println();
        System.out.print(WRITE_WINNING_STATISTICS_HEAD);
        System.out.println(result);
    }

    public static void writeYield(BigDecimal yield) {
        System.out.printf(WRITE_YIELD_FORMAT, yield.doubleValue());
    }

    private static String getRankPrintout(Rank rank, Integer count) {
        if (rank.equals(Rank.SECOND)) {
            return String.format(WRITE_MATCHING_RANK_WITH_BONUS_FORMAT,
                    rank.getMatchCount(), rank.getPrizeMoney(), count);
        }
        return String.format(WRITE_MATCHING_RANK_FORMAT,
                rank.getMatchCount(), rank.getPrizeMoney(), count);
    }

}
