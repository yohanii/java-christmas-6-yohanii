package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    @ParameterizedTest
    @CsvSource({"1, 1", "10, 10", "21, 21", "30, 30", "31, 31"})
    void validateDate_정상입력(String input, int answer) {
        int result = InputView.validateDate(input);

        assertThat(result).isEqualTo(answer);
    }

    @Test
    void validateDate_숫자가_아닌_인풋_예외처리() {
        String input = "123a";

        assertThatThrownBy(() -> InputView.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @CsvSource({"0", "-1", "-10", "-100"})
    void validateDate_1미만_인풋_예외처리(String input) {
        assertThatThrownBy(() -> InputView.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @CsvSource({"32", "33", "40", "100"})
    void validateDate_31초과_인풋_예외처리(String input) {
        assertThatThrownBy(() -> InputView.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void validateMenu_정상입력() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Order result = InputView.validateMenu(input);
        Map<Menu, Integer> resultStore = result.getOrderStore();

        assertThat(resultStore.get(Menu.T_BONE_STEAK)).isEqualTo(1);
        assertThat(resultStore.get(Menu.BARBECUE_RIB)).isEqualTo(1);
        assertThat(resultStore.get(Menu.CHOCO_CAKE)).isEqualTo(2);
        assertThat(resultStore.get(Menu.ZERO_COKE)).isEqualTo(1);
        assertThat(resultStore.size()).isEqualTo(4);
    }

    @Test
    void validateMenu_형식_오류_예외처리() {
        String input = "티본스테이크-1바비큐립-1초코케이크-2제로콜라-1";

        assertThatThrownBy(() -> InputView.validateMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateMenu_메뉴_개수_1미만_예외처리() {
        String input = "티본스테이크-0,바비큐립-1,초코케이크-2,제로콜라-1";

        assertThatThrownBy(() -> InputView.validateMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateMenu_중복_메뉴_예외처리() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,티본스테이크-1";

        assertThatThrownBy(() -> InputView.validateMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}