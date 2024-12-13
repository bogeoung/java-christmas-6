package christmas;


import Menu.TotalMenu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

    static Customer customer;
    static TotalMenu totalMenu;

    @BeforeAll
    static void setUp() {
        customer = new Customer();
        totalMenu = new TotalMenu();

        customer.add("양송이수프", 1);
        customer.add("타파스", 2);
        customer.add("제로콜라", 3);
        customer.add("해산물파스타", 4);
    }

    @Test
    @DisplayName("입력으로 받은 타입의 메뉴의 개수를 반환한다")
    void getNumberOfSpecificTypeTest() {
        String testType = "Menu.AppetizerMenu";
        int answer = 3;

        Assertions.assertThat(customer.getNumberOfSpecificType(totalMenu, testType)).isEqualTo(answer)
                .isEqualTo(answer);
    }
}