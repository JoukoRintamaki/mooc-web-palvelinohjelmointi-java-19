package hellosession;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {

    @RequestMapping("*")
    @ResponseBody
    public String sayHello(HttpSession httpSession) {
        int visits = 0;
        if (httpSession.getAttribute("count") != null) {
            visits = (int) httpSession.getAttribute("count");
        }
        visits++;
        httpSession.setAttribute("count", visits);
        return visits == 1 ? "Hello there!" : "Hello again!";
    }
}
