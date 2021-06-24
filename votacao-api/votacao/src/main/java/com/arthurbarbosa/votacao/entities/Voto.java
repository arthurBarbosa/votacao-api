package com.arthurbarbosa.votacao.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tb_voto")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O campo 'associado' é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    @NotNull(message = "O voto é obrigatório")
    @Column(nullable = false)
    private char voto;

    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;
}

