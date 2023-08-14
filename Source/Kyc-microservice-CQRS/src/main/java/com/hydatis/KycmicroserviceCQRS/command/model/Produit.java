package com.hydatis.KycmicroserviceCQRS.command.model;
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
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String libelle;
    private Integer codeProduit;
}
