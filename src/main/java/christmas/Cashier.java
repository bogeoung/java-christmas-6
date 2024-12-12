package christmas;

import Menu.TotalMenu;
import java.util.Arrays;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Cashier {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator validator;
    private final Customer customer;
    private final TotalMenu totalMenu;

    public Cashier(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = new InputValidator(outputView);
        this.customer = new Customer();
        this.totalMenu = new TotalMenu();
    }

    public void run() {
        outputView.printStartMessage();
        int date = getDate();
        acceptOrder();
        startPrintReceipt(date);
    }

    private int getDate() {
        boolean isValidate = false;
        String inputDate = "";
        while (!isValidate) {
            inputDate = inputView.readDate();
            isValidate = validator.dateValidate(inputDate);
        }
        return Integer.parseInt(inputDate);
    }

    private void acceptOrder() {
        List<String> menus = getMenus();
        for (String menu : menus) {
            List<String> menuInfo = Arrays.stream(menu.split(InputValidator.MENU_NUMBER_SEPARATOR)).toList();
            customer.add(menuInfo.get(0), Integer.parseInt(menuInfo.get(1)));
        }
    }

    private List<String> getMenus() {
        boolean isValidate = false;

        String inputMenu = "";
        while (!isValidate) {
            inputMenu = inputView.readMenu();
            isValidate = validator.menuValidate(inputMenu);
        }
        inputMenu = inputMenu.replace(" ", "");
        return Arrays.stream(inputMenu.split(",")).toList();
    }

    private void startPrintReceipt(int date) {
        PromotionCalculator promotionCalculator = new PromotionCalculator(customer, totalMenu);
        int totalPriceBeforeDiscount = promotionCalculator.calcTotalPriceBeforeDiscount();
        List<Integer> promotionPrices = promotionCalculator.calcPromotion(date);
        outputView.printMenu(promotionCalculator.getOrderMenu());
        outputView.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
        outputView.printFreeMenu(totalPriceBeforeDiscount);
        outputView.printPromotion(promotionPrices, promotionCalculator.isWeekDay(date));
        outputView.printTotalPriceAfterDiscount(totalPriceBeforeDiscount,promotionPrices);
        outputView.printBadge(promotionPrices);
    }
}

