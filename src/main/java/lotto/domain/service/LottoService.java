package lotto.domain.service;

import lotto.application.dto.PurchasedLottoDto;
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
    public PurchasedLottoDto purchase(Integer amount) {
        Money money = new Money(amount);
        int purchasedQuantity = money.calculatePurchasedQuantity();

        PurchasedLottos purchasedLottos = /// 만야겡 여기서 예외가 터진다면.. 다시 입력을 받아야하잖아
                PurchasedLottos.create(purchasedQuantity, LottoNumberMakerPort);

        return new PurchasedLottoDto(money, purchasedLottos);
    }

}
