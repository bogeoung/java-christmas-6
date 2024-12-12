package christmas;

import Menu.TotalMenu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import message.ErrorMessage;
import view.OutputView;

public class InputValidator {

    public static final int MINIMUM_DATE = 1;
    public static final int MAXIMUM_DATE = 31;
    public static final String MENU_SEPARATOR = ",";
    public static final String MENU_NUMBER_SEPARATOR = "-";
    private final OutputView outputView;
    private final TotalMenu totalMenu;
    private String input;

    public InputValidator(OutputView outputView) {
        this.outputView = outputView;
        this.totalMenu = new TotalMenu();
    }

    public boolean dateValidate(String inputDate) {
        int date;
        try {
            date = Integer.parseInt(inputDate);
        } catch (IllegalArgumentException e) {
            outputView.printInputDateError();
            return false;
        }
        if (date < MINIMUM_DATE || date > MAXIMUM_DATE) {
            outputView.printInputDateError();
            return false;
        }
        return true;
    }

    public boolean menuValidate(String inputMenu) {
        try {
            isValidInputForm(inputMenu);
            isInMenu(inputMenu);
            isDuplicateMenu(inputMenu);
            isValidNumberOfFood(inputMenu);
            isValidMenuType(inputMenu);
        } catch (IllegalArgumentException e) {
            outputView.printInputMenuError();
            return false;
        }
        return true;
    }

    private void isValidInputForm(String input) {
        String trimmedInput = input.replace(" ", "");
        if (!trimmedInput.matches("[가-힣]+-\\d(,[가-힣]+-\\d+)*")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_MENU_ERROR.getContent());
        }
        this.input = trimmedInput;
    }

    private void isInMenu(String inputMenu) {
        String menuName = input.split(MENU_NUMBER_SEPARATOR)[0];
        if (!totalMenu.hasMenu(menuName)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_MENU_ERROR.getContent());
        }
    }

    private void isDuplicateMenu(String inputMenu) {
        List<String> menus = Arrays.stream(input.split(MENU_SEPARATOR)).toList();
        for (String menu : menus) {
            if (menus.indexOf(menu) != menus.lastIndexOf(menu)) {
                throw new IllegalArgumentException(ErrorMessage.INPUT_MENU_ERROR.getContent());
            }
        }
    }

    private void isValidNumberOfFood(String inputMenu) {
        List<String> menus = Arrays.stream(input.split(MENU_SEPARATOR)).toList();
        int totalNumber = 0;
        for (String menu : menus) {
            int menuNumber = Integer.parseInt(Arrays.stream(menu.split(MENU_NUMBER_SEPARATOR)).toList().get(1));
            totalNumber += menuNumber;
        }
        if (totalNumber > 20) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_MENU_ERROR.getContent());
        }
    }

    private void isValidMenuType(String inputMenu) {
        Set<Optional<String>> menuTypes = new HashSet<>();

        List<String> menus = Arrays.stream(inputMenu.split(MENU_SEPARATOR)).toList();

        for (String menu : menus) {
            menuTypes.add(totalMenu.getMenuType(menu.split(MENU_NUMBER_SEPARATOR)[0].trim()));
        }
        ArrayList<Optional<String>> menuType = new ArrayList<>(menuTypes);
        if (menuType.size() == 1 && menuType.get(0).get().equals("Menu.DrinkMenu")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_MENU_ERROR.getContent());
        }
    }

}
