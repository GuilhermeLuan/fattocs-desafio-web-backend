package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.dtos.TaskGetResponse;
import dev.guilhermeluan.dtos.TaskPostRequest;
import dev.guilhermeluan.dtos.TaskPostResponse;
import dev.guilhermeluan.dtos.TaskPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    Task toTask(TaskPostRequest taskPostRequest);

    Task toTask(TaskPutRequest tarefaPutRequest);

    TaskGetResponse toTaskGetResponse(Task task);

    TaskPostResponse toTaskPostResponse(Task task);
}
