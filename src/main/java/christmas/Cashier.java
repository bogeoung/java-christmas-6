package christmas;

import java.util.Arrays;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Cashier {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator validator;
    private final Customer customer;

    public Cashier(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = new InputValidator(outputView);
        customer = new Customer();
    }

    public void run(){
        outputView.printStartMessage();
        int date = getDate();
        acceptOrder();
        System.out.println(customer);
    }

    private int getDate(){
        boolean isValidate = false;
        String inputDate = "";
        while(!isValidate) {
            inputDate = inputView.readDate();
            isValidate = validator.dateValidate(inputDate);
        }
        return Integer.parseInt(inputDate);
    }

    private void acceptOrder(){
        List<String> menus = getMenus();
        for(String menu:menus){
            List<String> menuInfo = Arrays.stream(menu.split(InputValidator.MENU_NUMBER_SEPARATOR)).toList();
            customer.add( menuInfo.get(0), Integer.parseInt(menuInfo.get(1)));
        }
    }

    private List<String> getMenus(){
        boolean isValidate = false;

        String inputMenu = "";
        while(!isValidate) {
            inputMenu = inputView.readMenu();
            isValidate = validator.menuValidate(inputMenu);
        }
        return Arrays.stream(inputMenu.split(",")).toList();
    }
}

