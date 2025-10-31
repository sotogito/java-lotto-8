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
            int matchingMainLottoCount = userLotto.matchMainLotto(purchasedLotto);
            boolean hasBonusNumber = userLotto.hasBonusNumberFrom(purchasedLotto);

            Rank rank = Rank.getRank(matchingMainLottoCount, hasBonusNumber);
            if (rank.equals(Rank.NOTHING)) {
                continue;
            }

            winningLottos.merge(rank, 1, Integer::sum);
        }
        return new WinningStatistics(winningLottos);
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner("\n");
        lottos.forEach(lotto -> result.add(lotto.toString()));

        return result.toString();
    }

}
