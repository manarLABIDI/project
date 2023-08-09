package com.hydatis.KycmicroserviceCQRS.command.entity;
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
public class SocietesAffilees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String SecteurActivite;
    private Double pourcentageCapitalDetenu;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    private AgentPersonneMorale societe;
}
