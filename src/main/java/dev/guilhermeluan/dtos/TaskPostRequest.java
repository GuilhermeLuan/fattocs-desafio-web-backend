package dev.guilhermeluan.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskPostRequest(
        @NotBlank(message = "The field 'taskName' is required")
        String taskName,
        @NotNull(message = "The field 'cost' is required")
        Double cost,
        @NotNull(message = "The field 'dataLimit' is required")
        Date dataLimit
) {
}