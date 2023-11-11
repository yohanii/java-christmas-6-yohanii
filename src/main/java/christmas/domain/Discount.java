package christmas.domain;

public class Discount {

    private final int totalDiscount;
    private final int christmasDDayDiscount;
    private final int dayOfWeekDiscount;
    private final int specialDiscount;
    private final int additionalEventDiscount;
    private final Badge eventBadge;

    public Discount() {
        this.totalDiscount = 0;
        this.christmasDDayDiscount = 0;
        this.dayOfWeekDiscount = 0;
        this.specialDiscount = 0;
        this.additionalEventDiscount = 0;
        this.eventBadge = Badge.NONE;
    }

    public Discount(int christmasDDayDiscount, int dayOfWeekDiscount, int specialDiscount, int additionalEventDiscount) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.dayOfWeekDiscount = dayOfWeekDiscount;
        this.specialDiscount = specialDiscount;
        this.additionalEventDiscount = additionalEventDiscount;

        this.totalDiscount = calculateTotalDiscount();
        this.eventBadge = Badge.getBadge(this.totalDiscount);
    }

    private int calculateTotalDiscount() {
        return christmasDDayDiscount + dayOfWeekDiscount + specialDiscount + additionalEventDiscount;
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
