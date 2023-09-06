package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Step1{
    private String firstname;
    private String lastname;
    private LocalDateTime ddn;
    private String Nationalite;
    private String AdressePersonnelle;
    private String AdresseCourrier;
    private boolean Resident;
    private String PaysResidence;
    private Long phone;
    private Long docid;
    private String DocumentType;
    private LocalDateTime DateEmission;
    private String LieuEmission;
    private String adresseEmail;
    private boolean PPE;
    private String Fonction;
    private boolean PPI;
    private String pays;
    private String raison;
    private String AgentType;
    private String employeur;
    private String adressePro;
    private String telFaxPro;
    private String emailPro;
    private String typeActivite;
    private String zoneActivite;

}
