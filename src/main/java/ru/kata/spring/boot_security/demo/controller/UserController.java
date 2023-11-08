package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {

    @RequestMapping("/user")
    public String viewUserForm(ModelMap model) {
        return "user";
    }

}





