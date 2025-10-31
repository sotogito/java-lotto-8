package lotto.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.function.Supplier;
import lotto.common.exception.RereadRequestException;
import org.junit.jupiter.api.Test;

class RereadExecutorTest {

    @Test
    void 재입력_없이_정상_반환() {
        Supplier<String> supplier = () -> "success";

        String actual = RereadExecutor.execute(supplier);
        String expected = "success";

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 재입력_횟수_초과로_예외처리() {
        int[] attempts = {0};
        Supplier<String> supplier = () -> {
            attempts[0]++;
            throw new RereadRequestException("실패");
        };

        assertThatThrownBy(() -> RereadExecutor.execute(supplier))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(attempts[0]).isEqualTo(50);
    }

}
