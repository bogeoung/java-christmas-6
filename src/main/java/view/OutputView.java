package view;

import christmas.PromotionCalculator;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import message.ErrorMessage;
import message.Message;

public class OutputView {

    public static final String NUBER_COUNT_UNIT = "개";
    public static final int FREE_PRODUCT_THRESHOLD = 120000;
    public static final String NONE_PROMOTION = "없음%s";
    public static final String NEGATIVE_NUMBER = "-";
    public static final DecimalFormat decimalFormat = new DecimalFormat("###,###원");

    public void printStartMessage() {
        System.out.println(Message.PROMOTION_START.getContent());
    }

    public void printInputDateError() {
        System.out.println(ErrorMessage.INPUT_DATE_ERROR.getContent());
    }

    public void printInputMenuError() {
        System.out.println(ErrorMessage.INPUT_MENU_ERROR.getContent());
    }

    public void printMenu(Map<String, Integer> orderMenu) {
        System.out.printf(Message.START_RECEIPT.getContent(), System.lineSeparator());
        System.out.printf("%s<주문 메뉴>%s", System.lineSeparator(), System.lineSeparator());
        for (Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + NUBER_COUNT_UNIT);
        }
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        System.out.printf("%s<할인 전 총주문 금액>%s", System.lineSeparator(), System.lineSeparator());
        System.out.println(decimalFormat.format(totalPrice));
    }

    public void printFreeMenu(int totalPrice) {
        System.out.printf("%s<증정 메뉴>%s", System.lineSeparator(), System.lineSeparator());
        if (totalPrice > FREE_PRODUCT_THRESHOLD) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.printf(NONE_PROMOTION, System.lineSeparator());
    }

    public void printPromotion(List<Integer> promotionPrices, boolean isWeekDay) {
        System.out.printf("%s<혜택 내역>%s", System.lineSeparator(), System.lineSeparator());

        int sumOfPromotions = getSumOfPromotionsPrices(promotionPrices);
        if (sumOfPromotions == 0) {
            System.out.printf(NONE_PROMOTION, System.lineSeparator());
            printPromotionPrice(0);
            return;
        }

        List<String> promotionNames = List.of("크리스마스 디데이 할인: ", "평일 할인: ", "주말 할인: ", "특별 할인: ", "증정 이벤트: ");
        for (int i = 0; i < promotionPrices.size(); i++) {
            if (promotionPrices.get(i) == 0) {
                continue;
            }
            String formattedPrice = decimalFormat.format(promotionPrices.get(i));
            System.out.println(promotionNames.get(i) + NEGATIVE_NUMBER + formattedPrice);
        }
        printPromotionPrice(sumOfPromotions);
    }

    private int getSumOfPromotionsPrices(List<Integer> promotionsPrices) {
        return promotionsPrices.stream().mapToInt(Integer::intValue).sum();
    }

    public void printPromotionPrice(int totalPrice) {
        System.out.printf("%s<총혜택 금액>%s", System.lineSeparator(), System.lineSeparator());
        if (totalPrice == 0) {
            System.out.printf(NONE_PROMOTION, System.lineSeparator());
            return;
        }
        System.out.println(NEGATIVE_NUMBER + decimalFormat.format(totalPrice));
    }

    public void printTotalPriceAfterDiscount(int totalPriceBeforeDiscount, List<Integer> promotionPrices) {
        System.out.printf("%s<할인 후 예상 결제 금액>%s", System.lineSeparator(), System.lineSeparator());
        String TotalPriceAfterDiscount = "";
        int promotionDiscountPrice = getSumOfPromotionsPrices(promotionPrices);
        if (totalPriceBeforeDiscount >= FREE_PRODUCT_THRESHOLD) {
            TotalPriceAfterDiscount = decimalFormat.format(
                    totalPriceBeforeDiscount + PromotionCalculator.FREE_PRODUCT_PRICE - promotionDiscountPrice);
        }
        if (totalPriceBeforeDiscount < FREE_PRODUCT_THRESHOLD) {
            TotalPriceAfterDiscount =
                    decimalFormat.format(totalPriceBeforeDiscount - promotionDiscountPrice);
        }
        System.out.println(TotalPriceAfterDiscount);
    }

    public void printBadge(List<Integer> promotionPrices) {
        System.out.printf("%s<12월 이벤트 배지>%s", System.lineSeparator(), System.lineSeparator());
        int sumOfPromotionPrices = getSumOfPromotionsPrices(promotionPrices);
        if (sumOfPromotionPrices >= 20000) {
            System.out.println("산타");
            return;
        }
        if (sumOfPromotionPrices >= 10000) {
            System.out.println("트리");
            return;
        }
        if (sumOfPromotionPrices >= 5000) {
            System.out.println("별");
        }
    }
}
