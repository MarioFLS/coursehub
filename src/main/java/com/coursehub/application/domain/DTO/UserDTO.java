package com.coursehub.application.domain.DTO;

import com.coursehub.application.domain.entities.User;

import java.util.UUID;

public record UserDTO(UUID id, String name, String email, String telephone, String taxIdentifier) {
    public User toEntity() {
        return new User(id, name, email, telephone, taxIdentifier);
    }
}
