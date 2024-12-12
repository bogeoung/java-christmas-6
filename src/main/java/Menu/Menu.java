package Menu;

import java.util.Map;

public class Menu {
    protected Map<String, Integer> menus;

    public boolean hasMenu(String input){
        return menus.containsKey(input);
    }

    int getPrice(String input){
        return menus.get(input);
    }

}
