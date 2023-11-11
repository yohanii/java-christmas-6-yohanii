package christmas.domain;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderStore;

    public Order(Map<Menu, Integer> orderStore) {
        validateDrinkOnly(orderStore);
        validateMenuCountOver(orderStore);
        this.orderStore = orderStore;
    }

    public Map<Menu, Integer> getOrderStore() {
        return orderStore;
    }

    public static void validateDrinkOnly(Map<Menu, Integer> orderStore) {
        boolean isDrinkOnly = orderStore.keySet()
                .stream()
                .allMatch(menu -> menu.getCategory().equals(Category.DRINK));
        if (isDrinkOnly) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMenuCountOver(Map<Menu, Integer> orderStore) {
        Integer menuCount = orderStore.values()
                .stream()
                .reduce((x, y) -> x + y)
                .orElse(0);
        if (menuCount > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
