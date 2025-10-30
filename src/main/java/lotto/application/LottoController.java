package lotto.application;

import java.math.BigDecimal;
import java.util.Map;
import lotto.common.constants.Rank;
import lotto.common.exception.RereadRequestException;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.vo.Money;
import lotto.ui.ExceptionHandler;
import lotto.ui.InputVIew;
import lotto.ui.OutputView;

public class LottoController {
    private final LottoUseCase lottoUseCase;

    public LottoController(LottoUseCase lottoUseCase) {
        this.lottoUseCase = lottoUseCase;
    }

    public void run() {
        Money money = createMoney();
        PurchasedLottos purchasedLottos = lottoUseCase.purchase(money);
        OutputView.writePurchaseLottos(money, purchasedLottos);

        UserLotto userLotto = createUseLotto();
        Map<Rank, Integer> winningStatistics =
                lottoUseCase.getWinningStatistics(userLotto, purchasedLottos);
        BigDecimal yield = lottoUseCase.calculateYield(money, winningStatistics);
        OutputView.writeWinningStatistics(winningStatistics);
        OutputView.writeYield(yield);
    }

    private Money createMoney() {
        while (true) {
            try {
                return new Money(InputVIew.readPurchaseAmount());
            } catch (RereadRequestException e) {
                ExceptionHandler.read(e);
            }
        }
    }

    private UserLotto createUseLotto() {
        Lotto mainLotto = createUserMainLotto();

        while (true) {
            try {
                return UserLotto.create(mainLotto, InputVIew.readBonusNumber());
            } catch (RereadRequestException e) {
                ExceptionHandler.read(e);
            }
        }
    }

    private Lotto createUserMainLotto() {
        while (true) {
            try {
                return new Lotto(InputVIew.readMainLottoNumbers());
            } catch (RereadRequestException e) {
                ExceptionHandler.read(e);
            }
        }
    }

}
