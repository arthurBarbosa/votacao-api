package com.arthurbarbosa.votacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tb_pauta")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @NotNull(message = "O campo 'descricao' é obrigatório")
    @Column(nullable = false)
    private String descricao;

    @OneToOne(mappedBy = "pauta")
    private Assembleia assembleia;
}
