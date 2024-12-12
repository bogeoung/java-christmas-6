package view;

import message.ErrorMessage;
import message.Message;

public class OutputView {

    public void printStartMessage() {
        System.out.println(Message.PROMOTION_START.getContent());
    }

    public void printInputDateError() {
        System.out.println(ErrorMessage.INPUT_DATE_ERROR.getContent());
    }

    public void printInputMenuError() {
        System.out.println(ErrorMessage.INPUT_MENU_ERROR.getContent());
    }

    public void printMenu() {
        System.out.printf(Message.START_RECEIPT.getContent(), System.lineSeparator());
        System.out.printf("%s<주문 메뉴>", System.lineSeparator());
    }

    public void printTotalPriceBeforeDiscount() {
        System.out.printf("%s<할인 전 총주문 금액>", System.lineSeparator());
    }

    public void printFreeMenu() {
        System.out.printf("%s<증정 메뉴>", System.lineSeparator());
    }

    public void printPromotion() {
        System.out.printf("%s<혜택 내역>", System.lineSeparator());
    }

    public void printPromotionPrice() {
        System.out.printf("%s<총혜택 금액>", System.lineSeparator());
    }

    public void printTotalPriceAfterDiscount() {
        System.out.printf("%s<할인 후 예상 결제 금액>", System.lineSeparator());
    }

    public void printBadge() {
        System.out.printf("%s<12월 이벤트 배지>", System.lineSeparator());
    }
}
