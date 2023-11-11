package christmas;

import christmas.domain.Discount;
import christmas.domain.Order;
import christmas.service.DiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        DiscountService discountService = new DiscountService();

        int day = InputView.readDate();
        Order order = InputView.readMenu();

        int totalPrice = Order.getTotalPrice(order);

        Discount totalDiscount = discountService.getDiscount(day, order);

        OutputView.printEvent(totalDiscount, totalPrice);
    }
}
