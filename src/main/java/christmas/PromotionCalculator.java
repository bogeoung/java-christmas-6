package christmas;

import Menu.TotalMenu;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PromotionCalculator {

    public static final int INIT_DDAY_PROMOTION_PRICE = 1000;
    public static final int DDAY_PROMOTION_PRICE_UNIT = 100;
    public static final int END_DAY_OF_DDAY_PROMOTION = 25;
    public static final int START_DAY_OF_DDAY_PROMOTION = 1;
    public static final int DAYS_OF_WEEK = 7;
    public static final int WEEKEND_REMAIN_VALUE = 2;
    Customer customer;
    TotalMenu totalMenu;

    public PromotionCalculator(Customer customer, TotalMenu totalMenu) {
        this.customer = customer;
        this.totalMenu = totalMenu;
    }

    public Map<String, Integer> getOrderMenu(){
        return customer.getOrderMenu();
    }

    public int calcTotalPriceBeforeDiscount(){
        int totalPriceBeforeDiscount = 0;
        for(String name : getOrderMenu().keySet()){
            int price = totalMenu.getMenuPrice(name);
            int menuCount = getOrderMenu().get(name);
            totalPriceBeforeDiscount += price * menuCount;
        }
        return totalPriceBeforeDiscount;
    }

    public List<Integer> calcPromotion(int date){
        List<Integer> promotionPrices = new LinkedList<>();
        //크리스마스 디데이 할인
        promotionPrices.add(calcDDayPromotion(date));
        //평일할인
        promotionPrices.add(calcWeekDayPromotion(date));
        //특별할인
        
        //증정 이벤트

        return promotionPrices;
    }

    private int calcDDayPromotion(int date){
        if(date>= START_DAY_OF_DDAY_PROMOTION && date <= END_DAY_OF_DDAY_PROMOTION){
            return INIT_DDAY_PROMOTION_PRICE + DDAY_PROMOTION_PRICE_UNIT * (date - 1);
        }
        return 0;
    }

    private int calcWeekDayPromotion(int date){
        if(!isWeekDay(date)){
            return getNumbersOfSpecificType("Menu.DessertMenu") * 2023;
        }
        return 0;
    }

    private boolean isWeekDay(int date){
        if(date % DAYS_OF_WEEK <= WEEKEND_REMAIN_VALUE){
            return true;
        }
        return false;
    }

    private int getNumbersOfSpecificType(String type){
        return customer.getNumberOfSpecificType(totalMenu, type);
    }
}
