package com.epam.task4.secret.repository;

import com.epam.task4.secret.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
