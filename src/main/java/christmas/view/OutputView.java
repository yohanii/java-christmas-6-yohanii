package christmas.view;

import christmas.domain.Discount;
import christmas.domain.Event;
import christmas.domain.Order;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    public static void printEvent(Discount discount, int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(toMoneyFormat(totalPrice));
        System.out.println("\n<증정 메뉴>");
        System.out.println(getAdditionalEventMenu(discount));
        System.out.println("\n<혜택 내역>");
        System.out.println(getDiscounts(discount));
        System.out.println("<총혜택 금액>");
        System.out.println(getTotalDiscount(discount));
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(toMoneyFormat(totalPrice - discount.getTotalDiscountExceptAdditionalEvent()));
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(discount.getEventBadge().getName());
    }

    private static String getTotalDiscount(Discount discount) {
        int totalDiscount = discount.getTotalDiscount();
        return totalDiscount == 0 ? "0원" : "-" + toMoneyFormat(totalDiscount);
    }

    private static String getDiscounts(Discount discount) {
        Map<String, Integer> discountValues = discount.values();

        String discountsFormat = discountValues.keySet()
                .stream()
                .filter(key -> discountValues.get(key) != 0)
                .reduce("", (x, y) -> x + Event.discountStringMap.get(y) + ": -" + toMoneyFormat(discountValues.get(y)) + "\n");
        if (discountsFormat.length() == 0) {
            return "없음\n";
        }
        return discountsFormat;
    }

    private static String getAdditionalEventMenu(Discount discount) {
        return discount.getAdditionalEventDiscount() > 0 ? Event.ADDITIONAL_EVENT_MENU.getName() + " 1개" : "없음";
    }

    public static void printMenu(int day, Order order) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        order.getOrderStore()
                .forEach((menu, quantity) -> System.out.println(menu.getName() + " " + quantity + "개"));
    }

    private static String toMoneyFormat(int price) {
        DecimalFormat formatter = new DecimalFormat("###,###원");
        return formatter.format(price);
    }
}
