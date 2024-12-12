package christmas;

import java.util.Arrays;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Cashier {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator validator;

    public Cashier(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = new InputValidator(outputView);
    }

    public void run(){
        outputView.printStartMessage();
        int date = getDate();
        List<String> menus = getMenus();
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

    private List<String> getMenus(){
        boolean isValidate = false;
        List<String> menus;

        String inputMenu = "";
        while(!isValidate) {
            inputMenu = inputView.readMenu();
            isValidate = validator.menuValidate(inputMenu);
        }
        return Arrays.stream(inputMenu.split(",")).toList();
    }
}

