package gifbin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DefaultController {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @GetMapping("/")
    public String redirect1() {
        return "redirect:/gifs";
    }

    @GetMapping("/gifs")
    public String redirect2() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String home1(Model model, @PathVariable Long id) {
        model.addAttribute("count", fileObjectRepository.count());
        if (fileObjectRepository.existsById(id - 1)) {
            model.addAttribute("previous", id - 1);
        }
        if (fileObjectRepository.existsById(id)) {
            model.addAttribute("current", id);
        }
        if (fileObjectRepository.existsById(id + 1)) {
            model.addAttribute("next", id + 1);
        }
        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "images/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return fileObjectRepository.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if ("image/gif".equals(multipartFile.getContentType())) {
            FileObject fileObject = new FileObject();
            fileObject.setContent(multipartFile.getBytes());
            fileObjectRepository.save(fileObject);
        }
        return "redirect:/gifs";
    }
}