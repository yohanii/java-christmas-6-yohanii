package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA(20000)
    , TREE(10000)
    , STAR(5000)
    , NONE(0);

    private final int min;

    Badge(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public static Badge getBadge(int price) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.getMin() <= price)
                .findFirst()
                .orElse(null);
    }
}
