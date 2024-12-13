package christmas;

import Menu.TotalMenu;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import view.OutputView;

public class PromotionCalculator {

    public static final int INIT_DDAY_PROMOTION_PRICE = 1000;
    public static final int DDAY_PROMOTION_PRICE_UNIT = 100;
    public static final int END_DAY_OF_DDAY_PROMOTION = 25;
    public static final int START_DAY_OF_DDAY_PROMOTION = 1;
    public static final int DAYS_OF_WEEK = 7;
    public static final int WEEKEND_REMAIN_VALUE = 2;
    public static final int WEEK_DAY_PROMOTION_UNIT = 2023;
    public static final int INIT_PRICE = 0;
    public static final int SPECIAL_DATE_PROMOTION_UNIT = 1000;
    public static final int CHRISTMAS_DATE = 25;
    public static final int REMAIN_NUMBER_OF_SUNDAY = 3;
    public static final int FREE_PRODUCT_PRICE = 25000;
    Customer customer;
    TotalMenu totalMenu;

    public PromotionCalculator(Customer customer, TotalMenu totalMenu) {
        this.customer = customer;
        this.totalMenu = totalMenu;
    }

    public int calcTotalPriceBeforeDiscount() {
        int totalPriceBeforeDiscount = INIT_PRICE;
        for (String name : getOrderMenu().keySet()) {
            int price = totalMenu.getMenuPrice(name);
            int menuCount = getOrderMenu().get(name);
            totalPriceBeforeDiscount += price * menuCount;
        }
        return totalPriceBeforeDiscount;
    }

    public boolean isWeekend(int date) {
        int remainDaysAfterDay1 = date % DAYS_OF_WEEK;
        return remainDaysAfterDay1 <= WEEKEND_REMAIN_VALUE && remainDaysAfterDay1 > 0;
    }

    public List<Integer> calcPromotion(int date) {
        List<Integer> promotionPrices = new LinkedList<>();
        List<Integer> weekDayPromotion = calcWeekDayPromotion(date);
        promotionPrices.add(calcDDayPromotion(date));
        promotionPrices.add(weekDayPromotion.get(0));
        promotionPrices.add(weekDayPromotion.get(1));
        promotionPrices.add(calcSpecialPromotion(date));
        promotionPrices.add(calcFreePromotion());
        return promotionPrices;
    }

    private int calcDDayPromotion(int date) {
        if (date >= START_DAY_OF_DDAY_PROMOTION && date <= END_DAY_OF_DDAY_PROMOTION) {
            return INIT_DDAY_PROMOTION_PRICE + DDAY_PROMOTION_PRICE_UNIT * (date - 1);
        }
        return 0;
    }

    private List<Integer> calcWeekDayPromotion(int date) {
        if (!isWeekend(date)) {
            return List.of(getNumbersOfSpecificType("Menu.DessertMenu") * WEEK_DAY_PROMOTION_UNIT, INIT_PRICE);
        }
        return List.of(INIT_PRICE, getNumbersOfSpecificType("Menu.MainMenu") * WEEK_DAY_PROMOTION_UNIT);
    }

    private int calcSpecialPromotion(int date) {
        if (isSpecialDay(date)) {
            return SPECIAL_DATE_PROMOTION_UNIT;
        }
        return INIT_PRICE;
    }

    private boolean isSpecialDay(int date) {
        return date % DAYS_OF_WEEK == REMAIN_NUMBER_OF_SUNDAY || date == CHRISTMAS_DATE;
    }

    private Integer calcFreePromotion() {
        int totalPriceBeforeDiscount = calcTotalPriceBeforeDiscount();
        if (totalPriceBeforeDiscount > OutputView.FREE_PRODUCT_THRESHOLD) {
            return FREE_PRODUCT_PRICE;
        }
        return INIT_PRICE;
    }

    private int getNumbersOfSpecificType(String type) {
        return customer.getNumberOfSpecificType(totalMenu, type);
    }

    public Map<String, Integer> getOrderMenu() {
        return customer.getOrderMenu();
    }
}
