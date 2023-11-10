package christmas.domain;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderStore;

    public Order(Map<Menu, Integer> orderStore) {
        validateMenuExisted(orderStore);
        validateDrinkOnly(orderStore);
        validateMenuCountOver(orderStore);
        this.orderStore = orderStore;
    }

    public Map<Menu, Integer> getOrderStore() {
        return orderStore;
    }

    public boolean validateMenuExisted(Map<Menu, Integer> orderStore) {
        return true;
    }

    public boolean validateDrinkOnly(Map<Menu, Integer> orderStore) {
        return true;
    }

    public boolean validateMenuCountOver(Map<Menu, Integer> orderStore) {
        return true;
    }
}
