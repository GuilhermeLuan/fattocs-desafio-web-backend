package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Task;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskUtils {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(PATTERN);

    public List<Task> newTaskList() throws ParseException {
        var tarefa1 = Task.builder()
                .id(1L)
                .taskName("Tarefa 1")
                .cost(100.0)
                .dataLimit(DATE_FORMATTER.parse("2024-11-10"))
                .presentationOrder(1)
                .build();

        var tarefa2 = Task.builder()
                .id(2L)
                .taskName("Tarefa 2")
                .cost(200.0)
                .dataLimit(DATE_FORMATTER.parse("2024-11-10"))
                .presentationOrder(2)
                .build();

        var tarefa3 = Task.builder()
                .id(3L)
                .taskName("Tarefa 3")
                .cost(300.0)
                .dataLimit(DATE_FORMATTER.parse("2024-11-10"))
                .presentationOrder(3)
                .build();

        return new ArrayList<>(List.of(tarefa1, tarefa2, tarefa3));
    }

    public Task newTaskToSave() throws ParseException {
        return Task.builder()
                .id(1L)
                .taskName("Testes Unitarios")
                .cost(150.0)
                .dataLimit(DATE_FORMATTER.parse("2024-11-10"))
                .presentationOrder(1)
                .build();
    }
}