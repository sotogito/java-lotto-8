package lotto.domain.port.inbound;

import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;

public interface LottoUseCase {

    PurchasedLottos purchase(Money money);

}
