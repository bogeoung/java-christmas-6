package christmas;

import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Cashier cashier = new Cashier(inputView, outputView);
        cashier.run();
    }
}
