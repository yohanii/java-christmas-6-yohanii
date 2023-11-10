package christmas.domain;

public class Discount {

    private final int totalDiscount;
    private final int christmasDDayDiscount;
    private final int dayOfWeekDiscount;
    private final int specialDiscount;
    private final int additionalEventDiscount;
    private final Badge eventBadge;

    public Discount(int totalDiscount, int christmasDDayDiscount, int dayOfWeekDiscount, int specialDiscount, int additionalEventDiscount, Badge eventBadge) {
        this.totalDiscount = totalDiscount;
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.dayOfWeekDiscount = dayOfWeekDiscount;
        this.specialDiscount = specialDiscount;
        this.additionalEventDiscount = additionalEventDiscount;
        this.eventBadge = eventBadge;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayDiscount;
    }

    public int getDayOfWeekDiscount() {
        return dayOfWeekDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getAdditionalEventDiscount() {
        return additionalEventDiscount;
    }

    public Badge getEventBadge() {
        return eventBadge;
    }
}
