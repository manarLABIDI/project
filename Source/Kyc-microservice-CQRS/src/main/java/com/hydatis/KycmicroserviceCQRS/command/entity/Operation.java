package com.hydatis.KycmicroserviceCQRS.command.entity;

import com.hydatis.KycmicroserviceCQRS.command.entity.enummeration.TypeOperation;
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
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double montantMoyenHebdo;
    private Double montantMoyenMensuels;
    private Double montantMoyenJournaliers;
    private Boolean virementBancairePostaux;
    private Boolean virementInterne;
    private Boolean cash;
    private Boolean virement;
    private Boolean autreMoyen;
    private String autreMoyenValue;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @ManyToOne
    private Compte compte;
}