package lotto.domain.port.inbound;

import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;

public interface LottoUseCase {

    Money createMoney(Integer amount);

    PurchasedLottos purchase(Money money);

}
