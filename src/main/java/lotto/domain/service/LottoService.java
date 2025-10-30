package lotto.domain.service;

import lotto.domain.model.PurchasedLottos;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.Money;

public class LottoService implements LottoUseCase {
    private final LottoNumberMakerPort LottoNumberMakerPort;
//    private final WinningStatisticsService winningStatisticsService;

    public LottoService(LottoNumberMakerPort lottoNumberMakerPort) {
        this.LottoNumberMakerPort = lottoNumberMakerPort;
//        this.winningStatisticsService = new WinningStatisticsService();
    }

    @Override
    public PurchasedLottos purchase(Money money) {
        int purchasedQuantity = money.calculatePurchasedQuantity();

        return PurchasedLottos.create(purchasedQuantity, LottoNumberMakerPort);
    }

}
