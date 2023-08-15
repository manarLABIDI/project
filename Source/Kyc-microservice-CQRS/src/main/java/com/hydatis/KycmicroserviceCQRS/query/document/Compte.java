package com.hydatis.KycmicroserviceCQRS.query.document;

import com.hydatis.KycmicroserviceCQRS.command.model.enummeration.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enummeration.SourceAlimentation;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Compte {
    private Long idCompte;
    private String raison;
    private SourceAlimentation sourceAlimentation;
    private String autreSourceAlimentationValue;
    private EtatDeCompte typeDeCompte;
    private Operation operationDebit;
    private Operation operationCredit;
}
