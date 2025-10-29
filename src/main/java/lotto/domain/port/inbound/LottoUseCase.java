package lotto.domain.port.inbound;

import lotto.application.response.PurchasedLottoResponse;

public interface LottoUseCase {

    PurchasedLottoResponse purchase(Integer amount);

}
