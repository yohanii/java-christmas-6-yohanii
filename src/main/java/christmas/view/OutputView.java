package christmas.view;

import christmas.domain.Discount;
import christmas.domain.Event;
import christmas.domain.Order;

import java.text.DecimalFormat;

public class OutputView {

    public static void printEvent(Discount discount, int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(toMoneyFormat(totalPrice));
        System.out.println("\n<증정 메뉴>");
        System.out.println(getAdditionalEventMenu(discount));
        System.out.println("\n<혜택 내역>");
        printDiscounts(discount);
        System.out.println("\n<총혜택 금액>");
        printTotalDiscount(discount);
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(toMoneyFormat(totalPrice - discount.getTotalDiscountExceptAdditionalEvent()));
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(discount.getEventBadge().getName());
    }

    private static void printTotalDiscount(Discount discount) {
        int totalDiscount = discount.getTotalDiscount();
        if (totalDiscount == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println("-" + toMoneyFormat(totalDiscount));
    }

    private static void printDiscounts(Discount discount) {
        int christmasDDayDiscount = discount.getChristmasDDayDiscount();
        int weekDayDiscount = discount.getWeekDayDiscount();
        int weekEndDiscount = discount.getWeekEndDiscount();
        int specialDiscount = discount.getSpecialDiscount();
        int additionalEventDiscount = discount.getAdditionalEventDiscount();

        if (christmasDDayDiscount == 0 && weekDayDiscount == 0 && weekEndDiscount == 0 && specialDiscount == 0 && additionalEventDiscount == 0) {
            System.out.println("없음");
            return;
        }

        if (christmasDDayDiscount != 0) {
            System.out.println("크리스마스 디데이 할인: -" + toMoneyFormat(christmasDDayDiscount));
        }
        if (weekDayDiscount != 0) {
            System.out.println("평일 할인: -" + toMoneyFormat(weekDayDiscount));
        }
        if (weekEndDiscount != 0) {
            System.out.println("주말 할인: -" + toMoneyFormat(weekEndDiscount));
        }
        if (specialDiscount != 0) {
            System.out.println("특별 할인: -" + toMoneyFormat(specialDiscount));
        }
        if (additionalEventDiscount != 0) {
            System.out.println("증정 이벤트: -" + toMoneyFormat(additionalEventDiscount));
        }
    }

    private static String getAdditionalEventMenu(Discount discount) {
        if (discount.getAdditionalEventDiscount() > 0) {
            return Event.additionalEventMenu.getName() + " 1개";
        }
        return "없음";
    }

    public static void printMenu(int day, Order order) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        order.getOrderStore()
                .forEach((menu, quantity) -> System.out.println(menu.getName() + " " + quantity + "개"));
    }

    public static String toMoneyFormat(int price) {
        DecimalFormat formatter = new DecimalFormat("###,###원");
        return formatter.format(price);
    }
}
