package Menu;

import java.util.Map;

public class DessertMenu extends Menu {

    public DessertMenu() {
        this.menus = Map.of(
                "초코케이크", 15000,
                "아이스크림", 5000
        );
    }
}
