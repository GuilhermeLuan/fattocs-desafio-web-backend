package dev.guilhermeluan.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(unique = true, nullable = false)
    private String taskName;
    @Column(nullable = false)
    @PositiveOrZero
    private Double cost;
    @Column(nullable = false)
    private Date dataLimit;
    @Column(unique = true, nullable = false)
    private Integer presentationOrder;

}

