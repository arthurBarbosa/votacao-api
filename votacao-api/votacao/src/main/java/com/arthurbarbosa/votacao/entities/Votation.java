package com.arthurbarbosa.votacao.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VOTATION")
@Builder
public class Votation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOT_ID", nullable = false)
    private Long id;

    @Column(name = "VOT_VOTE", columnDefinition = "tinyint", nullable = false)
    private Boolean vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOT_ACD_ID", referencedColumnName = "ACD_ID", nullable = false)
    private Associate associate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOT_SES_ID", referencedColumnName = "SES_ID", nullable = false)
    private Session session;
}

