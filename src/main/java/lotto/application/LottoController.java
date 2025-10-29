package lotto.application;

import lotto.application.dto.PurchasedLottoDto;
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
        PurchasedLottoDto purchasedLottoDto = purchaseLotto();
        Money money = purchasedLottoDto.money();
        PurchasedLottos purchasedLottos = purchasedLottoDto.purchasedLottos();

        OutputView.writePurchaseLottos(money, purchasedLottos);

    }

    private PurchasedLottoDto purchaseLotto() {
        while (true) {
            try {
                return lottoUseCase.purchase(InputVIew.readPurchaseAmount());
            }catch (RereadRequestException e) {
                ExceptionHandler.read(e);
            }
        }
    }

}
