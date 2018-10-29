package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.nure.dao.UserDao;
import ua.nure.entity.Role;
import ua.nure.entity.User;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        User userFromDb = userDao.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists.");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userDao.save(user);

        return "redirect:/login";
    }
}
