package lotto.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.infrastructure.RandomNumberMaker;
import org.junit.jupiter.api.Test;

class PurchasedLottosTest {

    @Test
    void 로또_수량에_따른_구매로또_생성() {
        assertThat(
                PurchasedLottos.create(
                        3,
                        new RandomNumberMaker())
        ).isNotNull();
    }

}