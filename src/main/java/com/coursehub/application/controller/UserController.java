package com.coursehub.application.controller;

import com.coursehub.application.domain.DTO.UserCreateDTO;
import com.coursehub.application.domain.DTO.UserUpdateDTO;
import com.coursehub.application.domain.entities.User;
import com.coursehub.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDTO userDto) {
        User user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserUpdateDTO userDto) {
        try {
            UUID uuid = UUID.fromString(id);

        Optional<User> user = userService.updateUser(uuid, userDto);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);

            Optional<User> user = userService.removeUser(uuid);
            if (user.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(user.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<User> user = userService.getUser(uuid);
            if (user.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(user.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
