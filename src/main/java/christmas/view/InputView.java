package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {

    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    public static final int MIN_MENU_QUANTITY = 1;

    public static int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        try {
            return validateDate(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            return readDate();
        }
    }

    public static Order readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        try {
            return validateMenu(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            return readMenu();
        }
    }

    public static int validateDate(String input) {
        try {
            Integer date = Integer.valueOf(input);
            if (date < MIN_DATE || date > MAX_DATE) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static Order validateMenu(String input) {
        try {
            Map<Menu, Integer> store = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(str -> str.split("-"))
                    .collect(Collectors.toMap(elem -> findMenu(elem[0]), elem -> validateMenuQuantity(elem[1])));
            return new Order(store);
        } catch (NumberFormatException | IllegalStateException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static Menu findMenu(String input) {
        return Menu.of(input);
    }

    private static Integer validateMenuQuantity(String input) {
        Integer quantity = Integer.valueOf(input);
        if (quantity < MIN_MENU_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return quantity;
    }
}
