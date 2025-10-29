package lotto.domain.model;

public class BonusLotto {
    private final int number;


    public BonusLotto(Integer number) {
        validateNumberRange(number);
        this.number = number;
    }


    private void validateNumberRange(Integer number) {
        if (number == null || number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호 범위는 1~45까지 입니다.");
        }
    }

}
