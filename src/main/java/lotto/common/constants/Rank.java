package lotto.common.constants;

public enum Rank {
    FIFTH(5, 3, false, 5_000L),
    FOURTH(4, 4, false, 50_000L),
    THIRD(3, 5, false, 1_500_000L),
    SECOND(2, 5, true, 30_000_000L),
    FIRST(1, 6, false, 2_000_000_000L),
    NOTHING(-1, 0, false, 0L);

    private final int rank;
    private final int matchCount;
    private final boolean hasBonus;
    private final Long prizeMoney;

    Rank(int rank, int matchCount, boolean hasBonus, Long prizeMoney) {
        this.rank = rank;
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Rank getRank(int matchCount, boolean hasBonus) {
        if (matchCount == SECOND.matchCount && hasBonus == SECOND.hasBonus) {
            return SECOND;
        }

        for (Rank rank : Rank.values()) {
            if (rank.matchCount == matchCount) {
                return rank;
            }
        }
        return NOTHING;
    }

}
