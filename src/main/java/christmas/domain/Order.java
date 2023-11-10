package christmas.domain;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderStore;

    public Order(Map<Menu, Integer> orderStore) {
        this.orderStore = orderStore;
    }

    public Map<Menu, Integer> getOrderStore() {
        return orderStore;
    }
}
