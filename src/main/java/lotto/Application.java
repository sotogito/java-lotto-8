package lotto;

import lotto.application.LottoController;
import lotto.domain.port.inbound.LottoUseCase;
import lotto.domain.service.LottoService;
import lotto.infrastructure.RandomNumberMaker;

public class Application {
    public static void main(String[] args) {

        LottoUseCase lottoUseCase = new LottoService(new RandomNumberMaker());
        LottoController  lottoController = new LottoController(lottoUseCase);
        lottoController.run();
    }

}
