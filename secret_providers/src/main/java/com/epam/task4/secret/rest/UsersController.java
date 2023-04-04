package com.epam.task4.secret.rest;

import com.epam.task4.secret.dto.UserDto;
import com.epam.task4.secret.service.UserService;
import com.epam.task4.secret.service.LoginAttemptService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UsersController {

    private UserService userService;
    private LoginAttemptService loginAttemptService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/blockedUsers")
    @PreAuthorize("hasAuthority('VIEW_ADMIN')")
    public String adminUsers(Model model){
        List<UserDto> blockedUsers = userService.findAllUsers()
            .stream()
            .filter(u -> loginAttemptService.isBlocked(u.getEmail()))
            .collect(Collectors.toList());
        model.addAttribute("users", blockedUsers);
        return "blockerUsers";
    }

}
