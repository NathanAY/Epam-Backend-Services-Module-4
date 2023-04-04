package com.epam.task4.secret.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final String USER_IS_BLOCKED_MSG = "User is blocked";
    private static final String BAD_CREDENTIALS_MSG = "Bad credentials";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
        String errorMessage = BAD_CREDENTIALS_MSG;

        if (exception.getMessage().equalsIgnoreCase(USER_IS_BLOCKED_MSG)) {
            errorMessage = USER_IS_BLOCKED_MSG;
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}
