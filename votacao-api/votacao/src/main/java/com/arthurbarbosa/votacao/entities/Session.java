package com.arthurbarbosa.votacao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SESSION")
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SES_ID", nullable = false)
    private Long id;

    @Column(name = "SES_DURATION", nullable = false)
    private Long duration;

    @Column(name = "SES_ISOPEN", nullable = false)
    private boolean isOpen;

    @OneToOne
    @JoinColumn(name = "SES_SCH_ID", referencedColumnName = "SCH_ID", nullable = false)
    private Schedule schedule;

    @PrePersist
    public void prePersist() {
        this.isOpen = false;
    }
}