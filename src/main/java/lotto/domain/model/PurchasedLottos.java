package lotto.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.common.constants.Rank;
import lotto.domain.port.outbound.LottoNumberMakerPort;

public class PurchasedLottos {
    private final List<Lotto> lottos;

    private PurchasedLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> calculateWinningLotto(UserLotto userLotto) {
        EnumMap<Rank, Integer> winningLottos = new EnumMap<>(Rank.class);

        for (Lotto purchasedLotto : lottos) {
            int matchingMainLottoCount = userLotto.matchMainLotto(purchasedLotto);
            boolean hasBonusNumber = userLotto.hasBonusNumberFrom(purchasedLotto);
            Rank rank = Rank.getRank(matchingMainLottoCount, hasBonusNumber);

            winningLottos.merge(rank, 1, Integer::sum);
        }

        return Collections.unmodifiableMap(winningLottos);
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
