package lotto.domain.port.inbound;

import java.math.BigDecimal;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;

public interface LottoUseCase {

    PurchasedLottos purchase(Money money);

    WinningStatistics getWinningStatistics(UserLotto userLotto, PurchasedLottos purchasedLottos);

    BigDecimal calculateYield(Money money, WinningStatistics winningStatistics);

}
