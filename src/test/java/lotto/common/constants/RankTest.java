package lotto.common.constants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {
            "4,false",
            "4,true"
    }, delimiter = ',')
    void 로또_당첨순위_반환(int matchCount, boolean hasBonus) {
        Rank actual = Rank.getRank(matchCount, hasBonus);
        Rank expect = Rank.FOURTH;

        assertThat(actual).isEqualTo(expect);
    }

    @Test
    void 로또_당첨순위_2등_반환() {
        Rank actual = Rank.getRank(5, true);
        Rank expect = Rank.SECOND;

        assertThat(actual).isEqualTo(expect);
    }

}