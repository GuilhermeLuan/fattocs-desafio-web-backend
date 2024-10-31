package dev.guilhermeluan.utils;

import dev.guilhermeluan.domain.Tarefa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TarefaUtils {

    public List<Tarefa> newTarefaList() {
        var tarefa1 = Tarefa.builder()
                .id(1L)
                .nomeTarefa("Tarefa 1")
                .custo(100.0)
                .dataLimite(new Date())
                .ordemApresentacao(1)
                .build();

        var tarefa2 = Tarefa.builder()
                .id(2L)
                .nomeTarefa("Tarefa 2")
                .custo(200.0)
                .dataLimite(new Date())
                .ordemApresentacao(2)
                .build();

        var tarefa3 = Tarefa.builder()
                .id(3L)
                .nomeTarefa("Tarefa 3")
                .custo(300.0)
                .dataLimite(new Date())
                .ordemApresentacao(3)
                .build();

        return new ArrayList<>(List.of(tarefa1, tarefa2, tarefa3));
    }

    public Tarefa newTarefaToSave() {
        return Tarefa.builder()
                .id(99L)
                .nomeTarefa("Tarefa Nova")
                .custo(150.0)
                .dataLimite(new Date())
                .ordemApresentacao(4)
                .build();
    }
}