package com.example.fruitapp.controller;

import com.example.fruitapp.dto.UserRequest;

import com.example.fruitapp.entity.Role;
import com.example.fruitapp.entity.UserEntity;

import com.example.fruitapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(
            @RequestBody UserRequest request
    ) {

        UserEntity user = new UserEntity();

        user.setUsername(request.getUsername());

        user.setPassword(request.getPassword());

        user.setRole(
                Role.valueOf(
                        request.getRole()
                )
        );

        return userRepository.save(user);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateRole(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {

        UserEntity user = userRepository
                .findById(id)
                .orElse(null);

        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        user.setRole(
                Role.valueOf(
                        request.getRole()
                )
        );

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable Long id
    ) {

        userRepository.deleteById(id);
    }
}

