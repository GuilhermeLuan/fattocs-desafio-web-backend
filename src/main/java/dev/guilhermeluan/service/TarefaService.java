package dev.guilhermeluan.service;

import dev.guilhermeluan.domain.Tarefa;
import dev.guilhermeluan.exception.NotFoundException;
import dev.guilhermeluan.repository.TarefaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class TarefaService {
    private final TarefaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Tarefa> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Tarefa findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não foi encontrada!"));
    }

    @Transactional
    public Tarefa save(Tarefa tarefa) {
        tarefa.setOrdemApresentacao(generateNextOrdemApresentacao());
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

    @Transactional
    public Integer generateNextOrdemApresentacao() {
        Integer maxOrdem = (Integer) entityManager
                .createQuery("SELECT COALESCE(MAX(t.ordemApresentacao), 0) FROM Tarefa t")
                .getSingleResult();
        return maxOrdem + 1;
    }
}
