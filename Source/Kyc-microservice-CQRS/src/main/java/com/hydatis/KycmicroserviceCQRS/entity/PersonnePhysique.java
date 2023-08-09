package com.hydatis.KycmicroserviceCQRS.entity;

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
public class PersonnePhysique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String nationalite;
    private String payResidence;
    private String adressePerso;
    private Long numCIN;
    private LocalDateTime dateEmissionCIN;
    private String  lieuEmissionCIN;
    private Double pourcentageCapitalDetenu;

   @ManyToOne
   @JoinColumn(name = "associes_id")
   private AgentPersonneMorale societe;

    @ManyToOne(optional = true)
    @JoinColumn(name = "actionneur_id")
    private PersonnePhysique personnePhysique;



}
