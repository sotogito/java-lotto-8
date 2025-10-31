package lotto.application;

import java.math.BigDecimal;
import lotto.common.util.RereadExecutor;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.UserLotto;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningStatistics;
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
        WinningStatistics winningStatistics =
                lottoUseCase.getWinningStatistics(userLotto, purchasedLottos);
        BigDecimal yield = lottoUseCase.calculateYield(money, winningStatistics);
        OutputView.writeWinningStatistics(winningStatistics);
        OutputView.writeYield(yield);

        InputVIew.consoleClose();
    }

    private Money createMoney() {
        return RereadExecutor.execute(() ->
                new Money(InputVIew.readPurchaseAmount()));
    }

    private UserLotto createUseLotto() {
        Lotto mainLotto = createUserMainLotto();

        return RereadExecutor.execute(() ->
                UserLotto.create(mainLotto, InputVIew.readBonusNumber()));
    }

    private Lotto createUserMainLotto() {
        return RereadExecutor.execute(() ->
                new Lotto(InputVIew.readMainLottoNumbers()));
    }

}
