package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private final DiscountService discountService = new DiscountService();
    private Order testOrder;

    @BeforeEach
    void setUp() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.CAESAR_SALAD, 1);
        orderStore.put(Menu.BARBECUE_RIB, 1);
        orderStore.put(Menu.T_BONE_STEAK, 1);
        orderStore.put(Menu.CHOCO_CAKE, 3);
        orderStore.put(Menu.ZERO_COKE, 3);
        testOrder = new Order(orderStore);
    }

    @ParameterizedTest
    @CsvSource({"1, 1000, 4046, 25000, 30046", "25, 3400, 6069, 25000, 34469", "31, 0, 6069, 25000, 31069"})
    void getDiscount(int day, int christmasDDayDiscount, int dayOfWeekDiscount, int specialDiscount, int additionalEventDiscount, int totalDiscount) {
        Discount result = discountService.getDiscount(day, testOrder);

        assertThat(result.getChristmasDDayDiscount()).isEqualTo(christmasDDayDiscount);
        assertThat(result.getDayOfWeekDiscount()).isEqualTo(dayOfWeekDiscount);
        assertThat(result.getSpecialDiscount()).isEqualTo(specialDiscount);
        assertThat(result.getAdditionalEventDiscount()).isEqualTo(additionalEventDiscount);
        assertThat(result.getEventBadge()).isEqualTo(Badge.SANTA);
        assertThat(result.getTotalDiscount()).isEqualTo(totalDiscount);
    }

    @ParameterizedTest
    @CsvSource({"1, 1000", "2, 1100", "10, 1900", "20, 2900", "25, 3400"})
    void getChristmasDDayDiscount_이벤트기간(int day, int answer) {
        int result = discountService.getChristmasDDayDiscount(day);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @CsvSource({"26", "27", "29", "30", "31"})
    void getChristmasDDayDiscount_이벤트기간_외(int day) {
        int result = discountService.getChristmasDDayDiscount(day);

        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"3", "4", "5", "6", "7"})
    void getDayOfWeekDiscount_평일(int day) {
        int result = discountService.getDayOfWeekDiscount(day, testOrder);

        assertThat(result).isEqualTo(2023 * 3);
    }

    @ParameterizedTest
    @CsvSource({"8", "9", "15", "16", "29", "30"})
    void getDayOfWeekDiscount_주말(int day) {
        int result = discountService.getDayOfWeekDiscount(day, testOrder);

        assertThat(result).isEqualTo(2023 * 2);
    }

    @ParameterizedTest
    @CsvSource({"3", "10", "17", "24", "25", "31"})
    void getSpecialDiscount_별_있는_날(int day) {
        int result = discountService.getSpecialDiscount(day);

        assertThat(result).isEqualTo(1000);
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "4", "20", "26"})
    void getSpecialDiscount_별_없는_날(int day) {
        int result = discountService.getSpecialDiscount(day);

        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"0", "10000", "40000", "100000", "119999"})
    void getAdditionalEventDiscount_120000_미만(int totalPrice) {
        int result = discountService.getAdditionalEventDiscount(totalPrice);

        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"120000", "130000", "150000", "250000", "400000"})
    void getAdditionalEventDiscount_120000_이상(int totalPrice) {
        int result = discountService.getAdditionalEventDiscount(totalPrice);

        assertThat(result).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @ParameterizedTest
    @CsvSource({"0", "1000", "2000", "3000", "4999"})
    void getEventBadge_5000미만(int totalDiscount) {
        Badge result = discountService.getEventBadge(totalDiscount);

        assertThat(result).isEqualTo(Badge.NONE);
    }

    @ParameterizedTest
    @CsvSource({"5000", "6000", "7500", "8000", "9999"})
    void getEventBadge_5000이상_10000미만(int totalDiscount) {
        Badge result = discountService.getEventBadge(totalDiscount);

        assertThat(result).isEqualTo(Badge.STAR);
    }

    @ParameterizedTest
    @CsvSource({"10000", "12000", "15000", "17500", "19999"})
    void getEventBadge_10000이상_20000미만(int totalDiscount) {
        Badge result = discountService.getEventBadge(totalDiscount);

        assertThat(result).isEqualTo(Badge.TREE);
    }

    @ParameterizedTest
    @CsvSource({"20000", "30000", "45000", "100000", "250000"})
    void getEventBadge_20000이상(int totalDiscount) {
        Badge result = discountService.getEventBadge(totalDiscount);

        assertThat(result).isEqualTo(Badge.SANTA);
    }
}