package dev.guilhermeluan.dtos;

import java.util.Date;

public record TarefaPutResponse(
        Long id,
        String nomeTarefa,
        Double custo,
        Date dataLimite,
        Integer ordemApresentacao

) {

}
