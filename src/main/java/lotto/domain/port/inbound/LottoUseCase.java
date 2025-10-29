package lotto.domain.port.inbound;

import java.util.List;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.vo.Money;

public interface LottoUseCase {

    Money createMoney(Integer amount);

    PurchasedLottos purchase(Money money);

    Lotto createUserMainLotto(List<Integer> numbers);

    UserLotto createUserLotto(Lotto mainLotto, int bonusNumber);

}
