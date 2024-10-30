package dev.guilhermeluan.dtos;

import java.util.Date;

public record TarefaPostResponse (
        Long id,
        String nomeTarefa,
        Double custo,
        Date dataLimite,
        Integer ordemApresentacao
){
}
