package lotto.domain.model;

import lotto.common.constants.LottoPolicy;
import lotto.common.exception.RereadRequestException;

public class UserLotto {
    private final Lotto mainLotto;
    private final int bonusNumber;

    private UserLotto(Lotto mainLotto, Integer bonusNumber) {
        this.mainLotto = mainLotto;
        this.bonusNumber = bonusNumber;
    }

    public static UserLotto create(Lotto mainLotto, Integer bonusNumber) {
        validateNotNull(mainLotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicateWithMainLotto(mainLotto, bonusNumber);

        return new UserLotto(mainLotto, bonusNumber);
    }

    public int matchMainLotto(Lotto purchasedLotto) {
        return mainLotto.getMatchingCount(purchasedLotto);
    }

    public boolean hasBonusNumberFrom(Lotto purchasedLotto) {
        return purchasedLotto.isContained(bonusNumber);
    }

    private static void validateNotNull(Lotto mainLotto, Integer bonusNumber) {
        if (mainLotto == null) {
            throw new IllegalArgumentException("예기치 못한 오류가 발생했습니다.");
        }
        if (bonusNumber == null) {
            throw new RereadRequestException("보너스 번호를 입력해주세요.");
        }
    }

    private static void validateBonusNumberRange(Integer bonusNumber) {
        if (bonusNumber < LottoPolicy.LOTTO_MIN_NUMBER
                || bonusNumber > LottoPolicy.LOTTO_MAX_NUMBER) {
            throw new RereadRequestException("로또 번호 범위는 1~45까지 입니다.");
        }
    }

    private static void validateBonusNumberDuplicateWithMainLotto(Lotto mainLotto, Integer bonusNumber) {
        if (mainLotto.isContained(bonusNumber)) {
            throw new RereadRequestException("보너스 번호와 6자리 로또는 중복될 수 없습니다.");
        }
    }

}
