package ru.jm.springBoot.controller;
// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jm.springBoot.model.User;
import ru.jm.springBoot.service.RoleService;

@Controller

public class UsersController {

    private final RoleService roleService;

    public UsersController(RoleService roleService) {

        this.roleService = roleService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAllUsers(ModelMap modelMap, @AuthenticationPrincipal User user) {

        modelMap.addAttribute("roles", roleService.getAllRoles());
        modelMap.addAttribute("user", user);
        return "adminPage";
    }

    @GetMapping("user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);

        return "userPage";
    }
}

