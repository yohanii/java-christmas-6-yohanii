package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {

    public static int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        return validateDate(Console.readLine());
    }

    public static Order readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        return validateMenu(Console.readLine());
    }

    public static int validateDate(String input) {
        return 0;
    }

    public static Order validateMenu(String input) {
        try {
            Map<Menu, Integer> store = Arrays.stream(input.split(","))
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
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return quantity;
    }
}
