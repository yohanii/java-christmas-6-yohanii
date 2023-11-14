package christmas.service;

import christmas.domain.*;

public class DiscountService {

    public static final int ZERO_DISCOUNT = 0;

    public Discount getDiscount(int day, Order order) {
        Integer totalPrice = Order.getTotalPrice(order);
        if (totalPrice < Event.MIN_EVENT_APPLY_PRICE) {
            return new Discount();
        }

        if (isWeekend(day)) {
            return new Discount(getChristmasDDayDiscount(day), ZERO_DISCOUNT, getDayOfWeekDiscount(day, order), getSpecialDiscount(day), getAdditionalEventDiscount(totalPrice));
        }
        return new Discount(getChristmasDDayDiscount(day), getDayOfWeekDiscount(day, order), ZERO_DISCOUNT, getSpecialDiscount(day), getAdditionalEventDiscount(totalPrice));
    }

    public int getChristmasDDayDiscount(int day) {
        return day <= Event.CHRISTMAS_D_DAY_DISCOUNT_END_DAY ? Event.CHRISTMAS_D_DAY_INITIAL_VALUE + Event.CHRISTMAS_D_DAY_INCREASE_VALUE * (day - 1) : ZERO_DISCOUNT;
    }

    public int getDayOfWeekDiscount(int day, Order order) {
        Category discountCategory = isWeekend(day) ? Category.MAIN : Category.DESSERT;

        return order.getOrderStore()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getCategory().equals(discountCategory))
                .map(entry -> entry.getValue() * Event.DAY_OF_WEEK_DISCOUNT_VALUE)
                .reduce((x, y) -> x + y)
                .orElse(ZERO_DISCOUNT);
    }

    private boolean isWeekend(int day) {
        int remain = day % 7;
        return remain == 1 || remain == 2;
    }

    public int getSpecialDiscount(int day) {
        if (Event.SPECIAL_DISCOUNT_DAYS.contains(day)) {
            return Event.SPECIAL_DISCOUNT_VALUE;
        }
        return ZERO_DISCOUNT;
    }

    public int getAdditionalEventDiscount(int totalPrice) {
        if (totalPrice >= Event.ADDITIONAL_EVENT_APPLY_PRICE) {
            return Event.ADDITIONAL_EVENT_MENU.getPrice();
        }
        return ZERO_DISCOUNT;
    }

    public Badge getEventBadge(int totalDiscount) {
        return Badge.getBadge(totalDiscount);
    }

}
