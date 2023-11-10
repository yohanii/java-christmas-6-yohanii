package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    MenuService menuService = new MenuService();

    @Test
    void getTotalPrice() {
        Map<Menu, Integer> orderStore = new HashMap<>();
        orderStore.put(Menu.CAESAR_SALAD, 1);
        orderStore.put(Menu.BARBECUE_RIB, 1);
        orderStore.put(Menu.CHOCO_CAKE, 2);
        orderStore.put(Menu.ZERO_COKE, 3);
        Order order = new Order(orderStore);

        int result = menuService.getTotalPrice(order);

        int totalPrice = Menu.CAESAR_SALAD.getPrice() + Menu.BARBECUE_RIB.getPrice() + Menu.CHOCO_CAKE.getPrice() * 2 + Menu.ZERO_COKE.getPrice() * 3;
        assertThat(result).isEqualTo(totalPrice);
    }
}