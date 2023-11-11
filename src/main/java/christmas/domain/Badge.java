package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000)
    , TREE("트리", 10000)
    , STAR("별", 5000)
    , NONE("없음", 0);

    private final String name;
    private final int min;

    Badge(String name, int min) {
        this.name = name;
        this.min = min;
    }

    public String getName() {
        return name;
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
