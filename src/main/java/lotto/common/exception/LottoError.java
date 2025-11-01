package lotto.common.exception;

public enum LottoError {
    // input
    INVALID_INPUT("잘못된 입력입니다."),
    INPUT_NOT_NUMBER("숫자로 입력해주세요."),

    // purchase money
    EMPTY_PURCHASED_MONEY("구매금액이 비어있습니다."),
    INVALID_PURCHASE_MONEY_RANGE("로또는 %,d~%,d원까지 구매할 수 있습니다."),
    INVALID_PURCHASE_MONEY_UNIT("구매금액은 %,d단위로 입력해주세요."),

    // lotto
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 %,d개여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호 범위는 %,d~%,d까지 입니다."),
    DUPLICATION_LOTTO_NUMBER("중복된 로또 번호가 있습니다."),
    EMPTY_BONUS_NUMBER("보너스 번호가 비어있습니다."),

    // BusinessException
    TOO_MANY_REQUEST_REINPUT("너무 많은 재입력을 요청했습니다."),
    GENERAL_ERROR("예기치 못한 오류가 발생했습니다.");

    private final String message;

    LottoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String formatMessage(Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        return String.format(message, args);
    }

}
