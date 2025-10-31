package lotto.domain.service;

import java.math.BigDecimal;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;

public class LottoService implements LottoUseCase {
    private final LottoNumberMakerPort LottoNumberMakerPort;

    public LottoService(LottoNumberMakerPort lottoNumberMakerPort) {
        this.LottoNumberMakerPort = lottoNumberMakerPort;
    }

    @Override
    public PurchasedLottos purchase(Money money) {
        int purchasedQuantity = money.calculatePurchasedQuantity();

        return PurchasedLottos.create(purchasedQuantity, LottoNumberMakerPort);
    }

    @Override
    public WinningStatistics getWinningStatistics(UserLotto userLotto, PurchasedLottos purchasedLottos) {
        return purchasedLottos.calculateWinningLotto(userLotto);
    }

    @Override
    public BigDecimal calculateYield(Money money, WinningStatistics winningStatistics) {
        long totalPrize = winningStatistics.calculateTotalPrize();

        return money.calculateYield(totalPrize);
    }

}
