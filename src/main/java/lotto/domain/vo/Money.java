package lotto.domain.vo;

import lotto.common.LottoPolicy;

public class Money {
    private final int amount;

    public Money(Integer amount) {
        validateAmount(amount);

        this.amount = amount;
    }

    private void validateAmount(Integer amount) {
        if (amount == null) {
            throw new IllegalArgumentException("구매금액이 비어있습니다.");
        }
        if (amount < LottoPolicy.LOTTO_MIN_PURCHASE_PRICE) {
            throw new IllegalArgumentException("로또는 최소 1장 이상 구매해야합니다.");
        }
        if (amount % LottoPolicy.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매금액은 1,000단위로 입력해주세요.");
        }
        if (amount > LottoPolicy.LOTTO_MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException("로또는 100장까지 구매할 수 있습니다.");
        }
    }

}
