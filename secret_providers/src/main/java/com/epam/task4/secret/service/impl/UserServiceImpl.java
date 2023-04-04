package com.epam.task4.secret.service.impl;

import com.epam.task4.secret.dto.UserDto;
import com.epam.task4.secret.entity.User;
import com.epam.task4.secret.entity.Authority;
import com.epam.task4.secret.repository.AuthoritiesRepository;
import com.epam.task4.secret.repository.UserRepository;
import com.epam.task4.secret.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthoritiesRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AuthoritiesRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Authority role = roleRepository.findByName("VIEW_ADMIN");
        if(role == null){
            role = checkAuthorityExist();
        }
        user.setAuthorities(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name.length > 2 ? name[1] : "");
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Authority checkAuthorityExist() {
        Authority authority = new Authority();
        authority.setName("VIEW_ADMIN");
        return roleRepository.save(authority);
    }
}
