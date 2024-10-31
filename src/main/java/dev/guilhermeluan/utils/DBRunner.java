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
                        .taskName("Planejar projeto")
                        .cost(130.50)
                        .dataLimit(Date.valueOf("2024-11-10"))
                        .presentationOrder(9)
                        .build(),

                Task.builder()
                        .taskName("Definir requisitos")
                        .cost(75.00)
                        .dataLimit(Date.valueOf("2024-11-12"))
                        .presentationOrder(2)
                        .build(),

                Task.builder()
                        .taskName("Criar wireframe")
                        .cost(50.00)
                        .dataLimit(Date.valueOf("2024-11-15"))
                        .presentationOrder(3)
                        .build(),

                Task.builder()
                        .taskName("Desenvolver backend")
                        .cost(100.00)
                        .dataLimit(Date.valueOf("2024-11-20"))
                        .presentationOrder(4)
                        .build(),

                Task.builder()
                        .taskName("Desenvolver frontend")
                        .cost(90.00)
                        .dataLimit(Date.valueOf("2024-11-25"))
                        .presentationOrder(5)
                        .build(),

                Task.builder()
                        .taskName("Implementar segurança")
                        .cost(60.00)
                        .dataLimit(Date.valueOf("2024-11-28"))
                        .presentationOrder(6)
                        .build(),

                Task.builder()
                        .taskName("Testes de integração")
                        .cost(40.00)
                        .dataLimit(Date.valueOf("2024-12-01"))
                        .presentationOrder(7)
                        .build(),

                Task.builder()
                        .taskName("Testes finais")
                        .cost(30.00)
                        .dataLimit(Date.valueOf("2024-12-05"))
                        .presentationOrder(8)
                        .build(),

                Task.builder()
                        .taskName("Entrega do projeto")
                        .cost(20.00)
                        .dataLimit(Date.valueOf("2024-12-10"))
                        .presentationOrder(1)
                        .build()

        ));

        log.info("Tarefas cadastradas!");
    }

}
