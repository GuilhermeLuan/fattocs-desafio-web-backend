package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Task;
import dev.guilhermeluan.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Component
public class DBRunner implements CommandLineRunner {
    private final TaskRepository repository;

    @Override
    public void run(String... args) {
        repository.saveAll(List.of(
                Task.builder()
                        .taskName("Tarefa 1")
                        .cost(100.0)
                        .dataLimit(Date.valueOf("2024-11-10"))
                        .presentationOrder(1)
                        .build(),

                Task.builder()
                        .taskName("Tarefa 2")
                        .cost(200.0)
                        .dataLimit(Date.valueOf("2024-11-10"))
                        .presentationOrder(2)
                        .build(),

                Task.builder()
                        .taskName("Tarefa 3")
                        .cost(300.0)
                        .dataLimit(Date.valueOf("2024-11-10"))
                        .presentationOrder(3)
                        .build()

        ));

        log.info("Tarefas cadastradas!");
    }

}
