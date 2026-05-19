package com.example.fruitapp.repository;

import com.example.fruitapp.entity.UserEntity;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
}