package dev.guilhermeluan.repository;

import dev.guilhermeluan.domain.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Page<Tarefa> findAll(Pageable pageable);
}
