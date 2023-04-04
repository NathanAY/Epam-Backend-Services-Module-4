package com.epam.task4.secret.rest;

import com.epam.task4.secret.dto.SecretDto;
import com.epam.task4.secret.dto.UserDto;
import com.epam.task4.secret.service.UserService;
import com.epam.task4.secret.service.impl.SecretService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@AllArgsConstructor
public class SecretController {

    private UserService userService;
    private SecretService secretService;

    @GetMapping("/secret")
    @PreAuthorize("isAuthenticated()")
    public String createSecret(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "secret";
    }

    @GetMapping("/secret/{secretLink}")
    @PreAuthorize("isAuthenticated()")
    public String getSecretByLink(@PathVariable String secretLink, Model model) {
        SecretDto secret = secretService.findByLinkNameAndDelete(secretLink);
        if (secret == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        model.addAttribute("secret", secret);
        return "one-time-secret";
    }

    @PostMapping("/secret")
    @PreAuthorize("isAuthenticated()")
    public String registration(@Valid @ModelAttribute("secret") SecretDto secret,
        BindingResult result, Model model) {
        String linkName = secretService.save(secret);
        model.addAttribute("linkName", "localhost:8080/secret/"+ linkName);
        return "secret-link";
    }

}
