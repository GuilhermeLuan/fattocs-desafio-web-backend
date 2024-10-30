package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.dtos.TarefaGetResponse;
import dev.guilhermeluan.dtos.TarefaPostRequest;
import dev.guilhermeluan.dtos.TarefaPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TarefaMapper {
    Tarefa toTarefa(TarefaPostRequest tarefaPostRequest);

    List<TarefaGetResponse> toTarefaGetResponseList(List<Tarefa> tarefa);

    TarefaGetResponse toTarefaGetResponse(Tarefa tarefa);

    TarefaPostResponse toTarefaPostResponse(Tarefa tarefa);

    TarefaPostRequest toTarefaPostRequest(Tarefa tarefa);
}
