package reloadheroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.UUID;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        if (session.getAttribute("reloadStatus") != null) {
            ReloadStatus reloadStatus = (ReloadStatus) session.getAttribute("reloadStatus");
            ReloadStatus reloadStatus1 = reloadStatusRepository.findByName(reloadStatus.getName());
            reloadStatus1.setReloads(reloadStatus1.getReloads()+1);
            reloadStatusRepository.save(reloadStatus1);
            session.setAttribute("reloadStatus",reloadStatus1);
            model.addAttribute("status", reloadStatus1);
        } else {
            ReloadStatus reloadStatus = new ReloadStatus(UUID.randomUUID().toString(), 1);
            reloadStatusRepository.save(reloadStatus);
            session.setAttribute("reloadStatus",reloadStatus);
            model.addAttribute("status", reloadStatus);
        }
        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        System.out.println(session);
        return "index";
    }
}