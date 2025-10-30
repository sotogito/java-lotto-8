package lotto.domain.service;

import java.util.Map;
import lotto.common.constants.Rank;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.Money;

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
    public Map<Rank, Integer> getWinningStatistics(UserLotto userLotto, PurchasedLottos purchasedLottos) {
        return purchasedLottos.calculateWinningLotto(userLotto);
    }

}
