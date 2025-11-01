package lotto.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.common.constants.LottoPolicy;
import lotto.common.exception.LottoError;
import lotto.common.exception.RereadRequestException;
import lotto.common.util.NumberValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateNumbersRange(numbers);
        validateDuplicationNumbers(numbers);

        this.numbers = numbers;
    }

    public int getMatchingCount(Lotto otherLotto) {
        Set<Integer> thisNumbers = new HashSet<>(this.numbers);
        Set<Integer> otherNumbers = new HashSet<>(otherLotto.numbers);

        thisNumbers.retainAll(otherNumbers);

        return thisNumbers.size();
    }

    public boolean isContained(Integer number) {
        return numbers.contains(number);
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (!NumberValidator.allNotNull(numbers)
                || !NumberValidator.hasExactSize(numbers, LottoPolicy.MAIN_LOTTO_NUMBER_COUNT)) {
            throw new RereadRequestException(LottoError.INVALID_LOTTO_NUMBER_COUNT,
                    LottoPolicy.MAIN_LOTTO_NUMBER_COUNT);
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (!NumberValidator.allInRange(numbers, LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER)) {
            throw new RereadRequestException(LottoError.INVALID_LOTTO_RANGE,
                    LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER);
        }
    }

    private void validateDuplicationNumbers(List<Integer> numbers) {
        if (!NumberValidator.hasDuplicate(numbers)) {
            throw new RereadRequestException(LottoError.DUPLICATION_LOTTO_NUMBER);
        }
    }

    @Override
    public String toString() {
        return numbers.stream()
                .sorted()
                .toList()
                .toString();
    }

}
