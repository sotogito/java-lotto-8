package lotto.application.dto;

import lotto.domain.model.PurchasedLottos;
import lotto.domain.vo.Money;

public record PurchasedLottoDto(
        Money money,
        PurchasedLottos purchasedLottos
) {
}
