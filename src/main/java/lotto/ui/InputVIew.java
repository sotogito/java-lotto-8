package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;

public class InputVIew {
    private final static String READ_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private final static String READ_MAIN_LOTTO_NUMBERS = "당첨 번호를 입력해 주세요.";
    private final static String READ_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        try {
            System.out.println(READ_PURCHASE_AMOUNT);

            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException(LottoError.INPUT_NOT_NUMBER);
        }
    }

    public static List<Integer> readMainLottoNumbers() {
        try {
            System.out.println(READ_MAIN_LOTTO_NUMBERS);

            return parseNumbers(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException(LottoError.INPUT_NOT_NUMBER);
        }
    }

    public static int readBonusNumber() {
        try {
            System.out.println();
            System.out.println(READ_BONUS_NUMBER);

            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException(LottoError.INPUT_NOT_NUMBER);
        }
    }

    public static void consoleClose() {
        Console.close();
    }

    private static String read() {
        try {
            String input = Console.readLine().trim();
            if (input.isEmpty()) {
                throw new RereadRequestException(LottoError.INVALID_INPUT);
            }
            return input;
        } catch (NullPointerException e) {
            throw new RereadRequestException(LottoError.INVALID_INPUT);
        }
    }

    private static List<Integer> parseNumbers(String inputNumbers) {
        List<Integer> lottoNumbers = new ArrayList<>();

        String[] splitNumbers = inputNumbers.split(",");
        for (String splitNumber : splitNumbers) {
            String number = splitNumber.trim();

            lottoNumbers.add(Integer.parseInt(number));
        }
        return Collections.unmodifiableList(lottoNumbers);
    }

}
