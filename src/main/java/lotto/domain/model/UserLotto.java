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
        if (bonusNumber == null) {
            throw new RereadRequestException("보너스 번호를 입력해주세요.");
        }
        if (mainLotto.isContained(bonusNumber)) {
            throw new RereadRequestException("보너스 번호와 6자리 로또는 중복될 수 없습니다.");
        }
        if (bonusNumber < LottoPolicy.LOTTO_MIN_NUMBER
                || bonusNumber > LottoPolicy.LOTTO_MAX_NUMBER) {
            throw new RereadRequestException("로또 번호 범위는 1~45까지 입니다.");
        }
        return new UserLotto(mainLotto, bonusNumber);
    }

}
