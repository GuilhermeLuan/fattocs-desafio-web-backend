package dev.guilhermeluan.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskPostRequest(
        @NotBlank(message = "The field 'taskName' is required")
        String taskName,
        @NotNull(message = "The field 'cost' is required")
        @DecimalMax(value = "999999999999999.00", message = "O custo não pode ser maior que 999999999999999.")
        Double cost,
        @NotNull(message = "The field 'dataLimit' is required")
        Date dataLimit
) {
}