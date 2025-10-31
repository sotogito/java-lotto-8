package lotto.ui;

import java.math.BigDecimal;
import java.util.Map;
import java.util.StringJoiner;
import lotto.common.constants.Rank;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;

public class OutputView {

    public static void writePurchaseLottos(Money money, PurchasedLottos purchasedLottos) {
        System.out.println();
        System.out.printf("%,d개를 구매했습니다.\n", money.calculatePurchasedQuantity());
        System.out.println(purchasedLottos.toString());
        System.out.println();
    }

    public static void writeWinningStatistics(WinningStatistics winningStatistics) {
        StringJoiner result = new StringJoiner("\n");

        Map<Rank, Integer> allRank = winningStatistics.getWinningStatisticsWithAllRank();
        for (Map.Entry<Rank, Integer> entry : allRank.entrySet()) {
            Rank rank = entry.getKey();
            Integer count = entry.getValue();

            if (rank.equals(Rank.SECOND)) {
                result.add(String.format("%,d개 일치, 보너스 볼 일치 (%,d원) - %,d개",
                        rank.getMatchCount(), rank.getPrizeMoney(), count));
                continue;
            }
            result.add(String.format("%,d개 일치 (%,d원) - %,d개",
                    rank.getMatchCount(), rank.getPrizeMoney(), count));
        }

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(result.toString());
    }

    public static void writeYield(BigDecimal yield) {
        System.out.printf("총 수익률은 %,.1f%%입니다.", yield.doubleValue());
    }

}
