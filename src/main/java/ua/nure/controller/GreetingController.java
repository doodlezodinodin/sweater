package ua.nure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET) //слушает
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Default World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting.mustache"; //возвращает
    }

    @GetMapping()
    public String main(Map<String, Object> model) {
        model.put("some", "hi");
        return "main";
    }
}
