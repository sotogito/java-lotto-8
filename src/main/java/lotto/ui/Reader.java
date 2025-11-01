package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;

public class Reader {
    private final String input;

    private Reader(String input) {
        this.input = input;
    }

    public static Reader read() {
        try {
            String input = Console.readLine().trim();
            if (input.isEmpty()) {
                throw new RereadRequestException(LottoError.INVALID_INPUT);
            }
            return new Reader(input);
        } catch (NullPointerException e) {
            throw new RereadRequestException(LottoError.INVALID_INPUT);
        }
    }

    public String getInput() {
        return input;
    }

}
