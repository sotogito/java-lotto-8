package lotto.domain.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.StringJoiner;
import lotto.common.constants.Rank;
import lotto.domain.port.outbound.LottoNumberMakerPort;
import lotto.domain.vo.WinningStatistics;

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

    public WinningStatistics calculateWinningLotto(UserLotto userLotto) {
        EnumMap<Rank, Integer> winningLottos = new EnumMap<>(Rank.class);

        for (Lotto purchasedLotto : lottos) {
            Rank rank = getRank(userLotto, purchasedLotto);

            winningLottos.merge(rank, 1, Integer::sum);
        }
        winningLottos.remove(Rank.NOTHING);
        return new WinningStatistics(winningLottos);
    }

    private Rank getRank(UserLotto userLotto, Lotto purchasedLotto) {
        int matchingMainLottoCount = userLotto.matchMainLotto(purchasedLotto);
        boolean hasBonusNumber = userLotto.hasBonusNumberFrom(purchasedLotto);

        return Rank.getRank(matchingMainLottoCount, hasBonusNumber);
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner("\n");
        lottos.forEach(lotto -> result.add(lotto.toString()));

        return result.toString();
    }

}
