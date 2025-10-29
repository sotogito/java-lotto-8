package lotto.domain.model;

import lotto.common.exception.RereadRequestException;

public class UserLotto {
    private final Lotto mainLotto;
    private final int bonusNumber;

    public UserLotto(Lotto mainLotto, Integer bonusNumber) {
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
        return new UserLotto(mainLotto, bonusNumber);
    }

}
