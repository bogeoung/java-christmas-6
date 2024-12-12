package Menu;

import java.util.Map;

public class MainMenu extends Menu {

    public MainMenu() {
        this.menus = Map.of(
                "티본스테이크", 55000,
                "바비큐립", 54000,
                "해산물파스타", 35000,
                "크리스마스파스타", 25000
        );
    }

}
