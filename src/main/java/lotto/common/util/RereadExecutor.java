package lotto.common.util;

import java.util.function.Supplier;
import lotto.common.exception.RereadRequestException;
import lotto.ui.ExceptionHandler;

public class RereadExecutor {
    private static final int MAX_RETRY_COUNT = 50;

    public static <T> T execute(Supplier<T> supplier) throws IllegalArgumentException {
        int retryCount = 0;

        while (retryCount < MAX_RETRY_COUNT) {
            try {
                return supplier.get();
            } catch (RereadRequestException e) {
                retryCount++;

                ExceptionHandler.read(e);
            }
        }
        throw new IllegalArgumentException("너무 많은 재입력을 요청했습니다.");
    }

}
