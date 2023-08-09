package com.hydatis.KycmicroserviceCQRS.command.entity;

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
public class AgentPersonnePhysique extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String adressePerso;
    private String addresseCourier;
    private Boolean estPPE;
    private String fonctionPpe;


}