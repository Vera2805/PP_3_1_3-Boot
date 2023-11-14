package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String getUsersForm(ModelMap model) {

        Set<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
       // return "admin";
        return "redirect:/admin";
    }

    @RequestMapping("/add")
    public String newUserForm(ModelMap model) {
        return "add";
    }



    @PostMapping("/newUser")
    public String newUserForm(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
          //  return "add";
            return "redirect:/admin";
        }
        Set<Role> roles = user.getRoles().stream().map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

     @PostMapping("/edit{id}")
      public String editUserForm(ModelMap model, @RequestParam Long id) {
          User user = userService.getUser(id);

         model.put("user", user);
        //  return "edit";
         return "redirect:/admin";
      }


    @PostMapping("/update")
    public String updateUserForm(@ModelAttribute("user") User user) {
        Set<Role> roles = user.getRoles().stream().map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/delete{id}")
    public String deleteUserForm(@RequestParam(required = true, defaultValue = "") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }




}



