package filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DefaultController {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/files";
    }

    @GetMapping("/files")
    public String get(Model model) {
        model.addAttribute("files", fileObjectRepository.findAll());
        return "files";
    }

    @PostMapping("/files")
    public String save(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        FileObject fileObject = new FileObject();
        fileObject.setName(multipartFile.getOriginalFilename());
        fileObject.setContentType(multipartFile.getContentType());
        fileObject.setContentLength(multipartFile.getSize());
        fileObject.setContent(multipartFile.getBytes());
        fileObjectRepository.save(fileObject);
        return "redirect:/files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> saveFile(@PathVariable Long id) {
        FileObject fileObject = fileObjectRepository.getOne(id);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(fileObject.getContentType()));
        httpHeaders.setContentLength(fileObject.getContentLength());
        httpHeaders.add("Content-Disposition","attachement; filename="+ fileObject.getName());
        return new ResponseEntity<>(fileObject.getContent(),httpHeaders, HttpStatus.CREATED);
    }
}