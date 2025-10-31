package lotto.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RandomNumberMakerTest {
    private final RandomNumberMaker randomNumberMaker = new RandomNumberMaker();

    @Test
    void 중복되지_않은_6자리_번호_생성() {
        List<Integer> actual = randomNumberMaker.make();

        assertThat(actual)
                .doesNotHaveDuplicates()
                .hasSize(6);
    }

}
