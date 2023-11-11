package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Order;

public class DiscountService {

    public Discount getDiscount(int day, Order order) {
        return null;
    }

    public int getChristmasDDayDiscount(int day) {
        return day <= 25 ? 1000 + 100 * (day - 1) : 0;
    }

    public int getDayOfWeekDiscount(int day, Order order) {
        return 0;
    }

    public int getSpecialDiscount(int day) {
        return 0;
    }

    public int getAdditionalEventDiscount(int totalPrice) {
        return 0;
    }

    public Badge getEventBadge(int totalDiscount) {
        return null;
    }

}
