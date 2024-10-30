package dev.guilhermeluan.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TarefaPostRequest (
        @NotBlank(message = "O campo 'nomeTarefa' é necessario")
        String nomeTarefa,
        @NotNull(message = "O campo 'custo' é necessario")
        Double custo,
        @NotNull(message = "O campo 'Date dataLimite' é necessario")
        Date dataLimite
) {
}