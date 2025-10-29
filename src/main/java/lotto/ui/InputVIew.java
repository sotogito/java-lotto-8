package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.exception.RereadRequestException;

public class InputVIew {

    public static int readPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해 주세요.");

            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException("구매 금액을 숫자로 입력해주세요.");
        }
    }

    private static String read() {
        try {
            String input = Console.readLine().trim();
            if (input.isEmpty()) {
                throw new RereadRequestException("잘못된 입력입니다.");
            }

            return input;
        } catch (NullPointerException e) {
            throw new RereadRequestException("잘못된 입력입니다.");
        }
    }

}
