package dev.guilhermeluan.dtos;

import java.util.Date;

public record TarefaGetResponse(
        Long id,
        String nomeTarefa,
        Double custo,
        Date dataLimite
) {
}
