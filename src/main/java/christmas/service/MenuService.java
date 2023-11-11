package christmas.service;

import christmas.domain.Order;

public class MenuService {

    public Integer getTotalPrice(Order order) {
        return order.getOrderStore()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce((x, y) -> x + y)
                .orElse(0);
    }
}
