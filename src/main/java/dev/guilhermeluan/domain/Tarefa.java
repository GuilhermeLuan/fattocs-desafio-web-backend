package dev.guilhermeluan.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(unique = true, nullable = false)
    private String nomeTarefa;
    @Column(nullable = false)
    private Double custo;
    @Column(unique = true, nullable = false)
    private Integer ordemApresentacao;
}

