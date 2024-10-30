package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.repository.TarefaRepository;
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
    private final TarefaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of(
                Tarefa.builder()
                        .nomeTarefa("Planejar projeto")
                        .custo(130.50)
                        .dataLimite(Date.valueOf("2024-11-10"))
                        .ordemApresentacao(9)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Definir requisitos")
                        .custo(75.00)
                        .dataLimite(Date.valueOf("2024-11-12"))
                        .ordemApresentacao(2)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Criar wireframe")
                        .custo(50.00)
                        .dataLimite(Date.valueOf("2024-11-15"))
                        .ordemApresentacao(3)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Desenvolver backend")
                        .custo(100.00)
                        .dataLimite(Date.valueOf("2024-11-20"))
                        .ordemApresentacao(4)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Desenvolver frontend")
                        .custo(90.00)
                        .dataLimite(Date.valueOf("2024-11-25"))
                        .ordemApresentacao(5)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Implementar segurança")
                        .custo(60.00)
                        .dataLimite(Date.valueOf("2024-11-28"))
                        .ordemApresentacao(6)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Testes de integração")
                        .custo(40.00)
                        .dataLimite(Date.valueOf("2024-12-01"))
                        .ordemApresentacao(7)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Testes finais")
                        .custo(30.00)
                        .dataLimite(Date.valueOf("2024-12-05"))
                        .ordemApresentacao(8)
                        .build(),

                Tarefa.builder()
                        .nomeTarefa("Entrega do projeto")
                        .custo(20.00)
                        .dataLimite(Date.valueOf("2024-12-10"))
                        .ordemApresentacao(1)
                        .build()

        ));

        log.info("Tarefas cadastradas!");
    }

}
