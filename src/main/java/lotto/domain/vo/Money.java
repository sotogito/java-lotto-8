package lotto.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lotto.common.constants.LottoPolicy;
import lotto.common.constants.YieldPolicy;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;
import lotto.common.util.NumberValidator;

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
        if (!NumberValidator.notNull(amount)) {
            throw new RereadRequestException(LottoError.EMPTY_PURCHASE_MONEY);
        }
    }

    private void validateAmountRange(Integer amount) {
        if (!NumberValidator.inRange(amount, LottoPolicy.MIN_PURCHASE_PRICE, LottoPolicy.MAX_PURCHASE_PRICE)) {
            throw new RereadRequestException(LottoError.INVALID_PURCHASE_MONEY_RANGE,
                    LottoPolicy.MIN_PURCHASE_PRICE, LottoPolicy.MAX_PURCHASE_PRICE);
        }
    }

    private void validateAmountUnit(Integer amount) {
        if (!NumberValidator.isDivisibleBy(amount, LottoPolicy.PRICE)) {
            throw new RereadRequestException(LottoError.INVALID_PURCHASE_MONEY_UNIT, LottoPolicy.PRICE);
        }
    }

}
