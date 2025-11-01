package lotto.common.exception;

public class RereadRequestException extends IllegalArgumentException {

    public RereadRequestException(LottoError lottoError, Object... args) {
        super(lottoError.formatMessage(args));
    }

}
