package lotto.application;

import lotto.common.exception.RereadRequestException;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.vo.Money;
import lotto.ui.ExceptionHandler;
import lotto.ui.InputVIew;
import lotto.ui.OutputView;

public class LottoController {
    private final LottoUseCase lottoUseCase;

    public LottoController(LottoUseCase lottoUseCase) {
        this.lottoUseCase = lottoUseCase;
    }

    public void run() {
        Money money = createMoney();
        PurchasedLottos purchasedLottos = lottoUseCase.purchase(money);

        OutputView.writePurchaseLottos(money, purchasedLottos);

    }

    private Money createMoney() {
        while (true) {
            try {
                return lottoUseCase.createMoney(InputVIew.readPurchaseAmount());
            } catch (RereadRequestException e) {
                ExceptionHandler.read(e);
            }
        }
    }

}
