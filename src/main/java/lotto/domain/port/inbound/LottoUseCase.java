package lotto.domain.port.inbound;

import lotto.application.dto.PurchasedLottoDto;

public interface LottoUseCase {

    PurchasedLottoDto purchase(Integer amount);

}
