package com.epam.task4.secret.handler;

import com.epam.task4.secret.repository.UserRepository;
import com.epam.task4.secret.service.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private LoginAttemptService loginAttemptService;
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        Object principal = e.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String email = (String) principal;
            if (userRepository.findByEmail(email) != null) {
                loginAttemptService.loginFailed(email);
            }
        }
    }
}
