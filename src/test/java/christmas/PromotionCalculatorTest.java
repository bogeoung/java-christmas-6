package christmas;

import Menu.TotalMenu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PromotionCalculatorTest {

    static PromotionCalculator promotionCalculator;

    @BeforeAll
    static void setUp() {
        Customer customer = new Customer();
        TotalMenu totalMenu = new TotalMenu();

        customer.add("시저샐러드", 1);
        customer.add("제로콜라", 2);
        customer.add("아이스크림", 3);
        customer.add("해산물파스타", 4);
        promotionCalculator = new PromotionCalculator(customer, totalMenu);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8})
    void 주말이라면_true를_반환한다(int candidate) {
        Assertions.assertThat(promotionCalculator.isWeekend(candidate)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void 주말이_아니라면_false를_반환한다(int candidate) {
        Assertions.assertThat(promotionCalculator.isWeekend(candidate)).isFalse();
    }


    @Test
    void 주문한_메뉴들의_총합을_반환한다() {
        int answer = 8000 + 3000 * 2 + 5000 * 3 + 35000 * 4;
        Assertions.assertThat(promotionCalculator.calcTotalPriceBeforeDiscount()).isEqualTo(answer);
    }

    @ParameterizedTest(name = "{0}이 입력되었을 때")
    @ValueSource(ints = {26, 27})
    void 크리스마스가_지난날짜는_DdayPromotion_헤택의값이_0이다(int candidate) {
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("{0}이 입력되었을 때")
    void 크리스마스가_이전날짜는_DdayPromotion_헤택의값이_날마다_100원씩_증가한다() {
        int day1DDayPromotion = promotionCalculator.calcPromotion(1).get(0);
        int day5DDayPromotion = promotionCalculator.calcPromotion(5).get(0);

        Assertions.assertThat(day1DDayPromotion).isEqualTo(1000);
        Assertions.assertThat(day5DDayPromotion - day1DDayPromotion).isEqualTo(400);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(ints = {1, 2})
    void 주말이_입력으로_들어오면_메인메뉴당_2023원이_할인된다(int candidate) {
        int answer = 2023 * 4;
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(1)).isEqualTo(0);
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(2)).isEqualTo(answer);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(ints = {3, 11})
    void 주말이_아닌날이_입력으로_들어오면_디저트메뉴당_2023원이_할인된다(int candidate) {
        int answer = 2023 * 3;
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(1)).isEqualTo(answer);
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(2)).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 달력에_별이있는_날은_특별할인이_적용된다(int candidate) {
        int answer = 1000;
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(3)).isEqualTo(answer);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(ints = {1, 2, 29, 30})
    void 달력에_별이없는_날은_특별할인이_적용되지_않는다(int candidate) {
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(3)).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} 이 입력되었을 때")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 총주문금액이_12만원_이상이면_샴페인이_무료로_제공된다(int candidate) {
        int answer = 25000;
        Assertions.assertThat(promotionCalculator.calcPromotion(candidate).get(4)).isEqualTo(answer);
    }

}