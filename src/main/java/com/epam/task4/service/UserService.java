package com.epam.task4.service;

import com.epam.task4.dto.UserDto;
import com.epam.task4.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
