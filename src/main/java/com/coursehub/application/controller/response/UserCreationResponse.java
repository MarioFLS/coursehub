package com.coursehub.application.controller.response;

import java.util.UUID;

public record UserCreationResponse(UUID id, String username, String email) {
}
