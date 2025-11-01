package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputVIew {
    private final static String READ_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private final static String READ_MAIN_LOTTO_NUMBERS = "당첨 번호를 입력해 주세요.";
    private final static String READ_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(READ_PURCHASE_AMOUNT);

        return NumberParser.parseNumber(Reader.read());
    }

    public static List<Integer> readMainLottoNumbers() {
        System.out.println(READ_MAIN_LOTTO_NUMBERS);

        return NumberParser.parseNumbers(Reader.read());
    }

    public static int readBonusNumber() {
        System.out.println();
        System.out.println(READ_BONUS_NUMBER);

        return NumberParser.parseNumber(Reader.read());
    }

    public static void consoleClose() {
        Console.close();
    }

}
