package com.epam.task4.secret.repository;

import com.epam.task4.secret.entity.Secret;
import com.epam.task4.secret.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretRepository extends JpaRepository<Secret, Long> {
    Secret findByLinkName(String linkName);
}
