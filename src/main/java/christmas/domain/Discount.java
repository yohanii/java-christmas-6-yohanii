package christmas.domain;

public class Discount {

    private final int totalDiscount;
    private final int totalDiscountExceptAdditionalEvent;
    private final int christmasDDayDiscount;
    private final int weekDayDiscount;
    private final int weekEndDiscount;
    private final int specialDiscount;
    private final int additionalEventDiscount;
    private final Badge eventBadge;

    public Discount() {
        this.totalDiscount = 0;
        this.totalDiscountExceptAdditionalEvent = 0;
        this.christmasDDayDiscount = 0;
        this.weekDayDiscount = 0;
        this.weekEndDiscount = 0;
        this.specialDiscount = 0;
        this.additionalEventDiscount = 0;
        this.eventBadge = Badge.NONE;
    }

    public Discount(int christmasDDayDiscount, int weekDayDiscount, int weekEndDiscount, int specialDiscount, int additionalEventDiscount) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.weekDayDiscount = weekDayDiscount;
        this.weekEndDiscount = weekEndDiscount;
        this.specialDiscount = specialDiscount;
        this.additionalEventDiscount = additionalEventDiscount;

        this.totalDiscount = calculateTotalDiscount();
        this.totalDiscountExceptAdditionalEvent = this.totalDiscount - additionalEventDiscount;
        this.eventBadge = Badge.getBadge(this.totalDiscount);
    }

    private int calculateTotalDiscount() {
        return christmasDDayDiscount + weekDayDiscount + weekEndDiscount + specialDiscount + additionalEventDiscount;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getTotalDiscountExceptAdditionalEvent() {
        return totalDiscountExceptAdditionalEvent;
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayDiscount;
    }

    public int getWeekDayDiscount() {
        return weekDayDiscount;
    }

    public int getWeekEndDiscount() {
        return weekEndDiscount;
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
