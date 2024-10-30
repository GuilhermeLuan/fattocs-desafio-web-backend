package dev.guilhermeluan.service;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.exception.NotFoundException;
import dev.guilhermeluan.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class TarefaService {
    private final TarefaRepository repository;

    public Page<Tarefa> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Tarefa findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não foi encontrada!"));
    }

    public Tarefa save(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public void update(Tarefa tarefa) {
        assertTarefaExist(tarefa.getId());
        repository.save(tarefa);
    }

    public void delete(Long id) {
        assertTarefaExist(id);
        repository.deleteById(id);
    }

    public void assertTarefaExist(Long id) {
        findByIdOrThrowBadRequestException(id);
    }
}
