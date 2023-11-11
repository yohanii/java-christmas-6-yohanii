package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void validateDrinkOnly_정상입력() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.CAESAR_SALAD, 1);
        orderStore.put(Menu.BARBECUE_RIB, 1);
        orderStore.put(Menu.CHOCO_CAKE, 2);
        orderStore.put(Menu.ZERO_COKE, 3);

        assertThatCode(() -> Order.validateDrinkOnly(orderStore))
                .doesNotThrowAnyException();
    }

    @Test
    void validateDrinkOnly_음료만_입력() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.ZERO_COKE, 3);
        orderStore.put(Menu.CHAMPAGNE, 3);
        orderStore.put(Menu.RED_WINE, 3);

        assertThatThrownBy(() -> Order.validateDrinkOnly(orderStore))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateMenuCountOver_20개_이하_정상입력() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.CAESAR_SALAD, 5);
        orderStore.put(Menu.BARBECUE_RIB, 5);
        orderStore.put(Menu.CHOCO_CAKE, 5);
        orderStore.put(Menu.ZERO_COKE, 5);

        assertThatCode(() -> Order.validateMenuCountOver(orderStore))
                .doesNotThrowAnyException();
    }

    @Test
    void validateMenuCountOver_20개_초과_입력() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.CAESAR_SALAD, 5);
        orderStore.put(Menu.BARBECUE_RIB, 5);
        orderStore.put(Menu.CHOCO_CAKE, 5);
        orderStore.put(Menu.ZERO_COKE, 6);

        assertThatThrownBy(() -> Order.validateMenuCountOver(orderStore))
                .isInstanceOf(IllegalArgumentException.class);
    }
}