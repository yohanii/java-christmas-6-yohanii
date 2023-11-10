package christmas;

import christmas.domain.Discount;
import christmas.domain.Order;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        MenuService menuService = new MenuService();
        DiscountService discountService = new DiscountService();

        int date = InputView.readDate();
        Order order = InputView.readMenu();

        int totalPrice = menuService.getTotalPrice(order);

        Discount totalDiscount = discountService.getTotalDiscount();

        OutputView.printEvent(totalDiscount, totalPrice);
    }
}
