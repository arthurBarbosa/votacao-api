package com.arthurbarbosa.votacao.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_associado")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Associado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'descricao' é obrigatório")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O campo 'CPF' é obrigatório")
    private String cpf;
}
