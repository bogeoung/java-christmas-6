package christmas;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private final Map<String, Integer> orderMenu;

    public Customer() {
        this.orderMenu = new HashMap<>();
    }

    public void add(String name, int number){
        orderMenu.put(name, number);
    }

    @Override
    public String toString() {
        return "orderMenu=" + orderMenu;
    }
}
