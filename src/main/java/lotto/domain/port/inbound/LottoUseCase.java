package lotto.domain.port.inbound;

import java.util.Map;
import lotto.common.constants.Rank;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.vo.Money;

public interface LottoUseCase {

    PurchasedLottos purchase(Money money);

    Map<Rank, Integer> getWinningStatistics(UserLotto userLotto, PurchasedLottos purchasedLottos);

}
