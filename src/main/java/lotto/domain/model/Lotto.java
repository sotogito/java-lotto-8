package lotto.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.common.constants.LottoPolicy;
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
            throw new RereadRequestException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (!NumberValidator.allInRange(numbers, LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER)) {
            throw new RereadRequestException("로또 번호 범위는 1~45까지 입니다.");
        }
    }

    private void validateDuplicationNumbers(List<Integer> numbers) {
        if (!NumberValidator.hasDuplicate(numbers)) {
            throw new RereadRequestException("중복된 로또 번호가 있습니다.");
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
