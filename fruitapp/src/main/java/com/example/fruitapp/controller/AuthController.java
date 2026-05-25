package com.example.fruitapp.controller;

import com.example.fruitapp.dto.LoginRequest;
import com.example.fruitapp.dto.LoginResponse;

import com.example.fruitapp.entity.UserEntity;

import com.example.fruitapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        UserEntity user = userRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        if (!user.getPassword()
                .equals(request.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Wrong password");
        }

        LoginResponse response
                = new LoginResponse(
                        user.getUsername(),
                        user.getRole().name()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/permissions/{username}")
    public ResponseEntity<?> getPermissions(
            @PathVariable String username
    ) {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        return ResponseEntity.ok(

                Map.of(

                        "username",
                        user.getUsername(),

                        "role",
                        user.getRole(),

                        "permissions",
                        user.getRole().getPermissions()
                )
        );
    }
}

