package com.coursehub.application.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coursehub.application.domain.DTO.UserCreateDTO;
import com.coursehub.application.domain.DTO.UserUpdateDTO;
import com.coursehub.application.domain.entities.User;
import com.coursehub.application.infra.security.CustomUserDetails;
import com.coursehub.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDTO userDto) {
        User user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateDTO userDto, Authentication authentication) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String id = userDetails.getUserId();
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

    @DeleteMapping()
    public ResponseEntity<User> removeUser(Authentication authentication) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String id = userDetails.getUserId();
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

    @GetMapping("/by_id")
    @PreAuthorize("")
    public ResponseEntity<User> getUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String id = userDetails.getUserId();
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
