package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TarefaUtils {

    public List<Task> newTarefaList() {
        var tarefa1 = Task.builder()
                .id(1L)
                .taskName("Tarefa 1")
                .cost(100.0)
                .dataLimit(new Date())
                .presentationOrder(1)
                .build();

        var tarefa2 = Task.builder()
                .id(2L)
                .taskName("Tarefa 2")
                .cost(200.0)
                .dataLimit(new Date())
                .presentationOrder(2)
                .build();

        var tarefa3 = Task.builder()
                .id(3L)
                .taskName("Tarefa 3")
                .cost(300.0)
                .dataLimit(new Date())
                .presentationOrder(3)
                .build();

        return new ArrayList<>(List.of(tarefa1, tarefa2, tarefa3));
    }

    public Task newTarefaToSave() {
        return Task.builder()
                .id(99L)
                .taskName("Tarefa Nova")
                .cost(150.0)
                .dataLimit(new Date())
                .presentationOrder(4)
                .build();
    }
}