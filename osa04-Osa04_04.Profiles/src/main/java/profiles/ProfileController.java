package profiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Controller
public class ProfileController {


    @Autowired
    private Environment environment;

    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        String[] k = environment.getActiveProfiles();
        if (k != null && k.length > 0) {
            return k[0];
        } else
            return "";
    }

    @GetMapping("/")
    public String home(Model model) {
        String[] k = environment.getActiveProfiles();
        if (k != null && k.length > 0) {
            model.addAttribute("title", k[0]);
        }
        return "index";
    }
}
