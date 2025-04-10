package dev.guilhermeluan.dtos;

import dev.guilhermeluan.domain.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
