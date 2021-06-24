package com.arthurbarbosa.votacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_assembleia")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Assembleia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O campo 'descricao' é obrigatório")
    @Column(nullable = false)
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false)
    private Date dataCriacao;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "votacao_voto",
            joinColumns = @JoinColumn(name = "sessao_votacao_id"),
            inverseJoinColumns = @JoinColumn(name = "voto_id")
    )
    private List<Voto> votos;

    private Boolean ativa;
}
