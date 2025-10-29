package lotto.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateNumbersRange(numbers);
        validateDuplicationNumbers(numbers);

        this.numbers = numbers;
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number == null) {
                throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
            }
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호 범위는 1~45까지 입니다.");
            }
        }
    }

    private void validateDuplicationNumbers(List<Integer> numbers) {
        Set<Integer> noDuplication = new HashSet<>(numbers);
        if (noDuplication.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호를 허용하지 않습니다.");
        }
    }

}
