package Menu;

import java.util.Map;

public class DrinkMenu extends Menu {

    public DrinkMenu() {
        this.menus = Map.of(
                "제로콜라", 3000,
                "레드와인", 60000,
                "샴페인", 25000
        );
    }
}
