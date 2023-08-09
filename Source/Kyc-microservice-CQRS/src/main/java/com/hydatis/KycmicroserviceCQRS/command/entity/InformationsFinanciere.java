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
public class InformationsFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double capitalSociete;
    private Double chiffreAffaireTotal;
    private Double TotalPassif;
    private Double resultatNet;




}
