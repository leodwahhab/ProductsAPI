package com.example.cursoSpring.repository;

import com.example.cursoSpring.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByLogin(String login);
}
