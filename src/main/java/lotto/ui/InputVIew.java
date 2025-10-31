package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static List<Integer> readMainLottoNumbers() {
        try {
            System.out.println("당첨 번호를 입력해 주세요.");

            return parseNumbers(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException("로또 번호를 숫자로 입력해주세요.");
        }
    }

    public static int readBonusNumber() {
        try {
            System.out.println();
            System.out.println("보너스 번호를 입력해 주세요.");

            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            throw new RereadRequestException("보너스 번호를 숫자로 입력해주세요.");
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
