package christmas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import view.OutputView;

class InputValidatorTest {

    static InputValidator inputValidator;

    @BeforeAll
    static void setUp() {
        OutputView outputView = new OutputView();
        inputValidator = new InputValidator(outputView);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @NullAndEmptySource
    @ValueSource(strings = {"!", "0", "100", "-1"})
    void 날짜가_1과_31이하의_숫자가_아니라면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.dateValidate(candidate)).isFalse();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"1", "31", "5"})
    void 날짜가_1과_31이하의_숫자라면_true를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.dateValidate(candidate)).isTrue();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @NullAndEmptySource
    @ValueSource(strings = {"시저샐러드-!", "시저샐러드,타파스", "Salad-2", "시저샐러드-1 타파스-2","시저샐러드-1, 타파스-2,", "시저샐러드--1"})
    void 입력형식에_맞지않는_입력이_들어오면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isFalse();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"시저샐러드-1, 타파스-2", "   시저샐러드   -  1 ,  타파스  -  2 "})
    void 입력형식에_맞는_입력이_들어오면_true를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isTrue();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"뇨끼-1"})
    void 존재하지_않는_메뉴가_입력으로_들어오면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isFalse();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"타파스-1, 타파스-2"})
    void 중복된_메뉴가_입력으로_들어오면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isFalse();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"타파스-20", "타파스-10, 시저샐러드-10" })
    void 메뉴의_개수가_20개를_초과하면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isFalse();
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(strings = {"제로콜라-1", "제로콜라-1, 레드와인-1"})
    void 음료_메뉴만_시키면_false를_반환한다(String candidate) {
        Assertions.assertThat(inputValidator.menuValidate(candidate)).isFalse();
    }

}