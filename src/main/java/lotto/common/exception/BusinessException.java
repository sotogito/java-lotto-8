package lotto.common.exception;

public class BusinessException extends IllegalArgumentException {

    public BusinessException(LottoError lottoError) {
        super(lottoError.getMessage());
    }

}
