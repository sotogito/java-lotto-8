package lotto.ui;

import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;

public class OutputView {

    public static void writePurchaseLottos(Money money, PurchasedLottos purchasedLottos) {
        System.out.println();
        System.out.printf("%,d개를 구매했습니다.\n", money.calculatePurchasedQuantity());
        System.out.println(purchasedLottos.toString());
    }

}
