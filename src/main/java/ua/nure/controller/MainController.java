package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.nure.dao.MessageDao;
import ua.nure.entity.Message;

import java.util.Map;

@Controller
public class MainController {

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
    public String main(Map<String, Object> model) {
        Iterable<Message> allMessages = messageDao.findAll();
        model.put("messages", allMessages);
        return "main";
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageDao.save(message);

        Iterable<Message> allMessages = messageDao.findAll();
        model.put("messages", allMessages);

        return "main";
    }

    @PostMapping("/filterByTag")
    public String filterByTag(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;

        if (tag != null && !tag.isEmpty()) {
            messages = messageDao.findByTag(tag);
        } else {
            messages = messageDao.findAll();
        }

        model.put("messages", messages);
        return "main";
    }
}
