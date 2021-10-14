package ru.jm.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jm.springBoot.dto.UserDTO;
import ru.jm.springBoot.model.User;
import ru.jm.springBoot.service.RoleService;
import ru.jm.springBoot.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
@RestController
@RequestMapping("/api/users")
public class UsersRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    UsersRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User userById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public User newUser(@ModelAttribute UserDTO userDTO) {
        User user = new User(userDTO);
        user.setRoles(user.getRoles().stream()
            .map(role -> roleService.getByName(role.getName()))
            .collect(Collectors.toSet()));
        userService.save(user);
        return user;
    }

    @PutMapping
    public User editUser(@ModelAttribute UserDTO userDTO){
        User user = new User(userDTO);
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getByName(role.getName()))
                .collect(Collectors.toSet()));
        userService.edit(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        userService.delete(user);
    }
}
