package com.dreamteam.SchoolSite.repositories;

import com.dreamteam.SchoolSite.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String name);

    User findByActivationCode(String code);
}
