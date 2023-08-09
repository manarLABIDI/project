package com.hydatis.KycmicroserviceCQRS.command.entity;
import com.hydatis.KycmicroserviceCQRS.command.entity.enummeration.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.entity.enummeration.SourceAlimentation;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCompte;
    private String raison;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SourceAlimentation sourceAlimentation;
    private String autreSourceAlimentationValue;
    @Enumerated(EnumType.STRING)
    private EtatDeCompte typeDeCompte;
    @OneToOne
    private Operation operationDebit;

    @OneToOne
    private Operation operationCredit;
    @OneToOne(mappedBy = "compte")
    private AgentPersonnePhysique titulaire;

}
