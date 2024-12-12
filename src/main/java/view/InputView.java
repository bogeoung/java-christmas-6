package view;

import camp.nextstep.edu.missionutils.Console;
import message.Message;

public class InputView {
    public String readDate() {
        System.out.println(Message.GET_VISIT_DAY.getContent());
        return readLine();
    }

    public String readMenu(){
        System.out.println(Message.GET_MENU.getContent());
        return readLine();
    }

    private String readLine(){
        return Console.readLine();
    }

}
