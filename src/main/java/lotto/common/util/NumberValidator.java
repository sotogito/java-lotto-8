package lotto.common.util;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class NumberValidator {

    public static boolean notNull(Integer number) {
        return number != null;
    }

    public static boolean inRange(Integer number, Integer min, Integer max) {
        return number >= min && number <= max;
    }

    public static boolean isDivisibleBy(Integer number, Integer divisor) {
        return number % divisor == 0;
    }

    public static boolean allNotNull(List<Integer> numbers) {
        return numbers != null && numbers.stream().noneMatch(Objects::isNull);
    }

    public static boolean allInRange(List<Integer> numbers, Integer min, Integer max) {
        return numbers.stream().allMatch(number -> inRange(number, min, max));
    }

    public static boolean hasExactSize(List<Integer> numbers, Integer size) {
        return numbers.size() == size;
    }

    public static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.size() == new HashSet<>(numbers).size();
    }

}
