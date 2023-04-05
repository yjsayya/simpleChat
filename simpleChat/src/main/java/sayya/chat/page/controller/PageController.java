package sayya.chat.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class PageController {
    static HashMap<String,String> idList = new HashMap<>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/goMain")
    public String goMain(String nickName, HttpSession session) {
        // 아무 값이 안 들어왔을 때 ; 이렇게 걸러줘야한다
        if(nickName==null){
            return "index";
        }

        if(idList.get(nickName)!=null){
            return "index";
        }
        session.setAttribute("nickName",nickName);

        return "main";
    }
} // Page Controller