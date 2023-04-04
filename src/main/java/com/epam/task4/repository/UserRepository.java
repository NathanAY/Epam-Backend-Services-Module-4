package com.epam.task4.repository;

import com.epam.task4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String email);
}
