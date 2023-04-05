package sayya.chat.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
public class ChatController {

    // 모든 client가 메세지 내용을 공유하기 위해서 static으로 설정
    static ArrayList<ArrayList<String>> msgList = new ArrayList<>();


    // 1. front로 전송하기
    @GetMapping("/chat/getMsg")
    public ArrayList<ArrayList<String>> msg() {
        return msgList;
    }

    // 2. front로부터 받기
    @GetMapping("/chat/sendMsg")
    public void sendMsg(HttpSession session, String msg) {

        ArrayList<String> list = new ArrayList<>();
        // msg에 들어가야할 내용
        // 1. nickName - session에 있음
        // 2. msg - 이건 front에서 받음
        // 3. 현재 시간 --> 원래는 DB TimeStamp로 받아야하지만 여기선 java 상에서 받는걸로 구현

        if(msg==null||msg.equals(""))
            return;
        String nickName=String.valueOf(session.getAttribute("nickName"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        list.add(nickName);
        list.add(msg);
        list.add(dtf.format(now) );

        msgList.add(list);
    }



} // chat controller