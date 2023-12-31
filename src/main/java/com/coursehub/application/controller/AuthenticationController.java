package com.coursehub.application.controller;

import com.coursehub.application.controller.response.TokenResponse;
import com.coursehub.application.domain.DTO.AuthenticationUserDTO;
import com.coursehub.application.domain.entities.User;
import com.coursehub.application.service.AuthenticationService;
import com.coursehub.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthenticationUserDTO userDto) {
        Optional<User> user = userService.loginUser(userDto);
        if(user.isPresent()) {
            String token = authenticationService.generateToken(user.get());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.notFound().build();
    }

}
