package com.api.othon.model.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
