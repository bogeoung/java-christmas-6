package view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import message.ErrorMessage;
import message.Message;

public class OutputView {

    public static final String NUBER_COUNT_UNIT = "개";
    public static final int FREE_PRODUCT_THRESHOLD = 120000;

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
            System.out.println(entry.getKey() + entry.getValue() + NUBER_COUNT_UNIT);
        }
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###원");
        String formattedPrice = decimalFormat.format(totalPrice);
        System.out.printf("%s<할인 전 총주문 금액>%s", System.lineSeparator(), System.lineSeparator());
        System.out.println(formattedPrice);
    }

    public void printFreeMenu(int totalPrice) {
        System.out.printf("%s<증정 메뉴>%s", System.lineSeparator(), System.lineSeparator());
        if (totalPrice > FREE_PRODUCT_THRESHOLD) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printPromotion(List<Integer> promotionPrices) {
        System.out.printf("%s<혜택 내역%s>", System.lineSeparator(), System.lineSeparator());
    }

    public void printPromotionPrice() {
        System.out.printf("%s<총혜택 금액%s>", System.lineSeparator(), System.lineSeparator());
    }

    public void printTotalPriceAfterDiscount() {
        System.out.printf("%s<할인 후 예상 결제 금액%s>", System.lineSeparator(), System.lineSeparator());
    }

    public void printBadge() {
        System.out.printf("%s<12월 이벤트 배지%s>", System.lineSeparator(), System.lineSeparator());
    }
}
