package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.port.LottoNumberMakerPort;

public class PurchasedLottos {
    private final List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }


    public static PurchasedLottos create(int purchasedQuantity, LottoNumberMakerPort lottoNumberMaker) {
        List<Lotto> purchasedLottos = new ArrayList<>();

        for (int i = 0; i < purchasedQuantity; i++) {
            try {
                purchasedLottos.add(new Lotto(lottoNumberMaker.make()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("예기치 못한 오류가 발생했습니다.");
            }
        }
        return new PurchasedLottos(purchasedLottos);
    }

}
