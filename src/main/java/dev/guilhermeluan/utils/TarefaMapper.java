package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.dtos.TarefaGetResponse;
import dev.guilhermeluan.dtos.TarefaPostRequest;
import dev.guilhermeluan.dtos.TarefaPostResponse;
import dev.guilhermeluan.dtos.TarefaPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TarefaMapper {
    Tarefa toTarefa(TarefaPostRequest tarefaPostRequest);

    Tarefa toTarefa(TarefaPutRequest tarefaPutRequest);

    TarefaGetResponse toTarefaGetResponse(Tarefa tarefa);

    TarefaPostResponse toTarefaPostResponse(Tarefa tarefa);
}
