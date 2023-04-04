package com.epam.task4.service;

import com.epam.task4.entity.Authority;
import com.epam.task4.entity.User;
import com.epam.task4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private LoginAttemptService loginAttemptService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (loginAttemptService.isBlocked(email)) {
            throw new LockedException("User is blocked");
        }

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getAuthorities()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

