package christmas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event {

    public static final int EVENT_MONTH = 12;
    public static final List<Integer> SPECIAL_DISCOUNT_DAYS = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
    public static final int MIN_EVENT_APPLY_PRICE = 10000;
    public static final int CHRISTMAS_D_DAY_DISCOUNT_END_DAY = 25;
    public static final int CHRISTMAS_D_DAY_INITIAL_VALUE = 1000;
    public static final int CHRISTMAS_D_DAY_INCREASE_VALUE = 100;
    public static final int DAY_OF_WEEK_DISCOUNT_VALUE = 2023;
    public static final int SPECIAL_DISCOUNT_VALUE = 1000;
    public static final Menu ADDITIONAL_EVENT_MENU = Menu.CHAMPAGNE;
    public static final int ADDITIONAL_EVENT_APPLY_PRICE = 120000;
    public static final int MAX_MENU_COUNT = 20;
    public static final List<Integer> DAY_OF_WEEKEND_OF_FIRST_WEEK = List.of(1, 2);

    public static final Map<String, String> discountStringMap = Map.of(
            "christmasDDayDiscount", "크리스마스 디데이 할인",
            "weekDayDiscount", "평일 할인",
            "weekEndDiscount", "주말 할인",
            "specialDiscount", "특별 할인",
            "additionalEventDiscount", "증정 이벤트"
    );
}
