package lotto.common.util;

import java.util.function.Supplier;
import lotto.common.exception.BusinessException;
import lotto.common.exception.LottoError;
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
        throw new BusinessException(LottoError.TOO_MANY_REQUEST_REINPUT);
    }

}
