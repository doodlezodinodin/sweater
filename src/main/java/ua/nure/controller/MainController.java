package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.dao.MessageDao;
import ua.nure.entity.Message;
import ua.nure.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    private final MessageDao messageDao;

    @Autowired
    private MainController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET) //слушает
    public String greeting(Map<String, Object> model) {
        return "greeting"; //возвращает
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageDao.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageDao.findByTag(filter);
        } else {
            messages = messageDao.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }

        messageDao.save(message);

        Iterable<Message> allMessages = messageDao.findAll();
        model.addAttribute( "messages", allMessages);

        return "main";
    }
}
