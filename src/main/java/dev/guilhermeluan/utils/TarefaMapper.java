package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.dtos.TarefaGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TarefaMapper {
    List<TarefaGetResponse> toTarefaGetResponseList(List<Tarefa> tarefa);
    TarefaGetResponse toTarefaGetResponse(Tarefa tarefa);
}
