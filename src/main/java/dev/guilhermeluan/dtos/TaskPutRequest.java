package dev.guilhermeluan.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskPutRequest(
        @NotNull(message = "The field 'id' is required")
        Long id,
        @NotBlank(message = "The field 'taskName' is required")
        String taskName,
        @NotNull(message = "The field 'cost' is required")
        Double cost,
        @NotNull(message = "The field 'dataLimit' is required")
        Date dataLimit
) {
}
