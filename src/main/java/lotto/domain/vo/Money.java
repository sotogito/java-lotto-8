package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lotto.common.constants.LottoPolicy;
import lotto.common.constants.YieldPolicy;
import lotto.common.exception.RereadRequestException;

public class Money {
    private final int amount;

    public Money(Integer amount) {
        validateNotNull(amount);
        validateAmountRange(amount);
        validateAmountUnit(amount);

        this.amount = amount;
    }

    public int calculatePurchasedQuantity() {
        return amount / LottoPolicy.PRICE;
    }

    public BigDecimal calculateYield(long totalPrice) {
        return BigDecimal.valueOf(totalPrice)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(amount), YieldPolicy.SCALE, RoundingMode.HALF_UP);
    }

    private void validateNotNull(Integer amount) {
        if (amount == null) {
            throw new RereadRequestException("구매금액이 비어있습니다.");
        }
    }

    private void validateAmountRange(Integer amount) {
        if (amount < LottoPolicy.MIN_PURCHASE_PRICE
                || amount > LottoPolicy.MAX_PURCHASE_PRICE) {
            throw new RereadRequestException("로또는 1~100장까지 구매할 수 있습니다.");
        }
    }

    private void validateAmountUnit(Integer amount) {
        if (amount % LottoPolicy.PRICE != 0) {
            throw new RereadRequestException("구매금액은 1,000단위로 입력해주세요.");
        }
    }

}
