package com.example.cursoSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public record ProductDTO(@NotBlank String name, @NotNull BigDecimal value) {
}
