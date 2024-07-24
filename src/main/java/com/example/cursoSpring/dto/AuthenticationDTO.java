package com.example.cursoSpring.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String login, String password) {
}
