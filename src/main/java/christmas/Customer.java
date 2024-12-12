package christmas;

import Menu.TotalMenu;
import java.util.HashMap;
import java.util.Map;

public class Customer {

    private final Map<String, Integer> orderMenu;

    public Customer() {
        this.orderMenu = new HashMap<>();
    }

    public void add(String name, int number) {
        orderMenu.put(name, number);
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }

    public int getNumberOfSpecificType(TotalMenu totalMenu, String type) {
        int numberOfSpecificType = 0;
        for (Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
            if (totalMenu.getMenuType(entry.getKey()).get().equals(type)) {
                numberOfSpecificType += entry.getValue();
            }
        }
        return numberOfSpecificType;
    }

}
