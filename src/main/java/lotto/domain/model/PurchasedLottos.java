package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.port.outbound.LottoNumberMakerPort;

public class PurchasedLottos {
    private final List<Lotto> lottos;

    private PurchasedLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }


    public static PurchasedLottos create(int purchasedQuantity, LottoNumberMakerPort lottoNumberMaker) {
        List<Lotto> purchasedLottos = new ArrayList<>();

        for (int i = 0; i < purchasedQuantity; i++) {
            purchasedLottos.add(new Lotto(lottoNumberMaker.make()));
        }
        return new PurchasedLottos(purchasedLottos);
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }

}
