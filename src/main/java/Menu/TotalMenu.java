package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TotalMenu {

    List<Menu> totalMenu = new ArrayList<>();

    public TotalMenu() {
        totalMenu.add(new AppetizerMenu());
        totalMenu.add(new MainMenu());
        totalMenu.add(new DessertMenu());
        totalMenu.add(new DrinkMenu());
    }

    public boolean hasMenu(String inputMenu) {
        for (Menu menu : totalMenu) {
            if (menu.hasMenu(inputMenu)) {
                return true;
            }
        }
        return false;
    }

    public Optional<String> getMenuType(String inputMenu) {
        return totalMenu.stream().filter(menu -> menu.hasMenu(inputMenu)).findAny()
                .map(menu -> menu.getClass().getName());
    }

    public int getMenuPrice(String inputMenu) {
        return totalMenu.stream().filter(menu -> menu.hasMenu(inputMenu)).findAny()
                .map(menu -> menu.getPrice(inputMenu)).orElse(0);
    }
}
