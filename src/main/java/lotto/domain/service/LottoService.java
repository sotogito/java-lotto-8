package lotto.domain.service;

import java.math.BigDecimal;
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

    @Override
    public BigDecimal calculateYield(Money money, Map<Rank, Integer> winningStatistics) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : winningStatistics.entrySet()) {
            Rank rank = entry.getKey();
            if(rank.equals(Rank.NOTHING)) {
                continue;
            }
            long prize = rank.getPrizeMoney();

            totalPrize += prize;
        }
        return money.calculateYield(totalPrize);
    }

}
