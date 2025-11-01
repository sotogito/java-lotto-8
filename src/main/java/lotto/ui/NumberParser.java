package lotto.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;

public class NumberParser {
    private final static String DELIMITER = ",";

    public static Integer parseNumber(Reader reader) {
        return parseNumber(reader.getInput());
    }

    public static List<Integer> parseNumbers(Reader reader) {
        List<Integer> numbers = new ArrayList<>();

        String[] splitNumbers = reader.getInput().split(DELIMITER, -1);
        for (String splitNumber : splitNumbers) {
            String number = splitNumber.trim();

            numbers.add(parseNumber(number));
        }
        return Collections.unmodifiableList(numbers);
    }

    private static Integer parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new RereadRequestException(LottoError.INPUT_NOT_NUMBER);
        }
    }

}
