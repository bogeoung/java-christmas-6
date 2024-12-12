package Menu;

import java.util.Map;

public class AppetizerMenu extends Menu {

    public AppetizerMenu() {
        this.menus = Map.of(
                "양송이수프", 6000,
                "타파스", 5500,
                "시저샐러드", 8000
        );
    }
}
