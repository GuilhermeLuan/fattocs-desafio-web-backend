package dev.guilhermeluan.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder

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
    @Column(nullable = false)
    private Date dataLimite;
    @Column(unique = true, nullable = false)
    private Integer ordemApresentacao;

}

