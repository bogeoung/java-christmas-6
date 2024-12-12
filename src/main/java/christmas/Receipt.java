package christmas;

import Menu.TotalMenu;
import java.util.List;
import java.util.Map;

public class Receipt {

    private final PromotionCalculator promotionCalculator;
    private final int date;
    private final int totalPRiceBeforeDiscount;
    private final List<Integer> promotionDiscounts;
    private final Map<String, Integer> orderMenus;

    public Receipt(PromotionCalculator promotionCalculator, int date) {
        this.promotionCalculator = promotionCalculator;
        this.date = date;
        this.totalPRiceBeforeDiscount = promotionCalculator.calcTotalPriceBeforeDiscount();
        this.promotionDiscounts = promotionCalculator.calcPromotion(date);
        this.orderMenus = promotionCalculator.getOrderMenu();
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPRiceBeforeDiscount;
    }

    public List<Integer> getPromotionsDiscountPrices() {
        return promotionDiscounts;
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenus;
    }

    public boolean getIsWeekDay() {
        return promotionCalculator.isWeekend(date);
    }


}
