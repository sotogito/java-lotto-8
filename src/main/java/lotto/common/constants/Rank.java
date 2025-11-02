package lotto.common.constants;

import java.util.EnumMap;

public enum Rank {
    FIFTH(5, 3, false, 5_000L), //5천원
    FOURTH(4, 4, false, 50_000L), //5만원
    THIRD(3, 5, false, 1_500_000L), //150만원
    SECOND(2, 5, true, 30_000_000L), //3천만원
    FIRST(1, 6, false, 2_000_000_000L), //20억원
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

    public int getMatchCount() {
        return matchCount;
    }

    public Long getPrizeMoney() {
        return prizeMoney;
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

    public static EnumMap<Rank, Integer> initRankByCount() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
    }

}
