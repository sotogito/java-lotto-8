package lotto.domain.service;

import lotto.application.response.PurchasedLottoResponse;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.Money;

public class LottoService implements LottoUseCase {
    private final LottoNumberMakerPort LottoNumberMakerPort;

    public LottoService(LottoNumberMakerPort lottoNumberMakerPort) {
        this.LottoNumberMakerPort = lottoNumberMakerPort;
    }

    @Override
    public PurchasedLottoResponse purchase(Integer amount) {
        Money money = new Money(amount);
        int purchasedQuantity = money.calculatePurchasedQuantity();

        PurchasedLottos purchasedLottos =
                PurchasedLottos.create(purchasedQuantity, LottoNumberMakerPort);

        return new PurchasedLottoResponse(purchasedQuantity, purchasedLottos.toString());
    }

}
