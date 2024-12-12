package message;

public enum ErrorMessage {
    INPUT_DATE_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INPUT_MENU_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String content;

    ErrorMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return ERROR_MESSAGE_PREFIX + content;
    }

}

