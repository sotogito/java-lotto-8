package lotto.ui;

import java.util.Map;
import java.util.StringJoiner;
import lotto.common.constants.Rank;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;

public class OutputView {

    public static void writePurchaseLottos(Money money, PurchasedLottos purchasedLottos) {
        System.out.println();
        System.out.printf("%,d개를 구매했습니다.\n", money.calculatePurchasedQuantity());
        System.out.println(purchasedLottos.toString());
    }

    public static void writeWinningStatistics(Map<Rank, Integer> winningStatistics) {
        StringJoiner result = new StringJoiner("\n");

        for (Map.Entry<Rank, Integer> entry : Rank.initRankByCount().entrySet()) {
            Rank rank = entry.getKey();
            Integer count = entry.getValue();
            if (rank.equals(Rank.NOTHING)) {
                continue;
            }
            if (winningStatistics.containsKey(rank)) {
                count = winningStatistics.get(rank);
            }
            if (rank.equals(Rank.SECOND)) {
                result.add(String.format("%,d개 일치, 보너스 볼 일치 (%,d원) - %,d개",
                        rank.getMatchCount(), rank.getPrizeMoney(), count));
            }
            result.add(String.format("%,d개 일치 (%,d원) - %,d개",
                    rank.getMatchCount(), rank.getPrizeMoney(), count));
        }

        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(result.toString());
    }

}
