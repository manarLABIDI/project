package com.hydatis.KycmicroserviceCQRS.command.model;

import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDecision;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fraudScore;
    private LocalDateTime dateDecision;

    @OneToOne
    private DemandeEngagement demande;

    @Enumerated(EnumType.STRING)
    private TypeDecision decision;
    @ManyToOne
    private Responsable prisePar;
}
