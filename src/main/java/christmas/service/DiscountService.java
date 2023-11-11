package christmas.service;

import christmas.domain.*;

public class DiscountService {

    public Discount getDiscount(int day, Order order) {
        return null;
    }

    public int getChristmasDDayDiscount(int day) {
        return day <= 25 ? 1000 + 100 * (day - 1) : 0;
    }

    public int getDayOfWeekDiscount(int day, Order order) {
        Category discountCategory = isWeekend(day) ? Category.MAIN : Category.DESSERT;

        return order.getOrderStore()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getCategory().equals(discountCategory))
                .map(entry -> entry.getValue() * 2023)
                .reduce((x, y) -> x + y)
                .orElse(0);
    }

    private boolean isWeekend(int day) {
        int remain = day % 7;
        return remain == 1 || remain == 2;
    }

    public int getSpecialDiscount(int day) {
        if (Event.specialDiscountDays.contains(day)) {
            return 1000;
        }
        return 0;
    }

    public int getAdditionalEventDiscount(int totalPrice) {
        if (totalPrice >= Event.additionalEventStandardPrice) {
            return Event.additionalEventMenu.getPrice();
        }
        return 0;
    }

    public Badge getEventBadge(int totalDiscount) {
        return null;
    }

}
