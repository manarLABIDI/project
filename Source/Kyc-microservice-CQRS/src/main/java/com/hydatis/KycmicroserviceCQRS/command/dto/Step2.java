package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Step2{
    private String comptePaiement;
    private boolean virementInterne;
    private boolean virementBancaire;
    private boolean autreMoyenPaiement;
    private String autreMoyenPaiementText;
    private double montantsJournaliers;
    private double montantsHebdomadaires;
    private double montantsMensuels;
    private boolean cash;
    private boolean virementdebit;
    private boolean autreMoyendebit;
    private String autreMoyendebitText;
    private String montantsJournaliersdebit;
    private double montantsHebdomadairesdebit;
    private double montantsMensuelsdebit;
    private boolean virementespeces;
    private boolean RevenusPro;
    private boolean ProduitInvestissement;
    private boolean autresource;
    private String autreSourceText;


}
