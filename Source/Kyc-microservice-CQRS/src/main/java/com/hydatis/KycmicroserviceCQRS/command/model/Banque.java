package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String adresse;
    private String swift;
    private String rib;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "banqueEnRelation")
    private List<AgentPersonnePhysique> clientPersonnePhysique;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "banques")
    private List<AgentPersonneMorale> personneMorales;
}