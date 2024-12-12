package message;

public enum Message {
    PROMOTION_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    GET_VISIT_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    GET_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    START_RECEIPT("12월 [입력날짜]일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    private final String content;

    Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

