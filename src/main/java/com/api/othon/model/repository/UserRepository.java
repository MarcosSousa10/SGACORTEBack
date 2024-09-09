package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<com.api.othon.model.user.User, String> {
    UserDetails findByLogin(String login);
}
