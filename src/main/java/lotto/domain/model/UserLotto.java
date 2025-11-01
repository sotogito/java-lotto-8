package lotto.domain.model;

import lotto.common.constants.LottoPolicy;
import lotto.common.exception.BusinessException;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;
import lotto.common.util.NumberValidator;

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
            throw new BusinessException(LottoError.GENERAL_ERROR);
        }
        if (!NumberValidator.notNull(bonusNumber)) {
            throw new RereadRequestException(LottoError.EMPTY_BONUS_NUMBER);
        }
    }

    private static void validateBonusNumberRange(Integer bonusNumber) {
        if (!NumberValidator.inRange(bonusNumber, LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER)) {
            throw new RereadRequestException(LottoError.INVALID_LOTTO_RANGE,
                    LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER);
        }
    }

    private static void validateBonusNumberDuplicateWithMainLotto(Lotto mainLotto, Integer bonusNumber) {
        if (mainLotto.isContained(bonusNumber)) {
            throw new RereadRequestException(LottoError.DUPLICATION_LOTTO_NUMBER);
        }
    }

}
