package lotto.infrastructure;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.common.constants.LottoPolicy;
import lotto.domain.port.outbound.LottoNumberMakerPort;

public class RandomNumberMaker implements LottoNumberMakerPort {

    @Override
    public List<Integer> make() {
        return Randoms.pickUniqueNumbersInRange(
                LottoPolicy.MIN_NUMBER,
                LottoPolicy.MAX_NUMBER,
                LottoPolicy.MAIN_LOTTO_NUMBER_COUNT
        );
    }

}
