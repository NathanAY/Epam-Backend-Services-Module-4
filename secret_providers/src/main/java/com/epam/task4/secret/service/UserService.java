package com.epam.task4.secret.service;

import com.epam.task4.secret.dto.UserDto;
import com.epam.task4.secret.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
