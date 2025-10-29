package lotto.domain.service;

import java.util.List;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.Money;

public class LottoService implements LottoUseCase {
    private final LottoNumberMakerPort LottoNumberMakerPort;

    public LottoService(LottoNumberMakerPort lottoNumberMakerPort) {
        this.LottoNumberMakerPort = lottoNumberMakerPort;
    }

    @Override
    public Money createMoney(Integer amount) {
        return new Money(amount);
    }

    @Override
    public PurchasedLottos purchase(Money money) {
        int purchasedQuantity = money.calculatePurchasedQuantity();

        return PurchasedLottos.create(purchasedQuantity, LottoNumberMakerPort);
    }

    @Override
    public Lotto createUserMainLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    @Override
    public UserLotto createUserLotto(Lotto mainLotto, int bonusNumber) {
        return UserLotto.create(mainLotto, bonusNumber);
    }

}
