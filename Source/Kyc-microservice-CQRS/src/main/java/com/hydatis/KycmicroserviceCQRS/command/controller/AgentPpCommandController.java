package com.hydatis.KycmicroserviceCQRS.command.controller;

import com.hydatis.KycmicroserviceCQRS.command.dto.AgentPersonnePhysiqueDTO;
import com.hydatis.KycmicroserviceCQRS.command.model.*;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeOperation;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDocument;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.SourceAlimentation;

import com.hydatis.KycmicroserviceCQRS.command.service.implementation.AgentPpCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pp")
public class AgentPpCommandController {

    private final AgentPpCommandService agentPpCommandService;

    @Autowired
    public AgentPpCommandController(AgentPpCommandService agentPpCommandService) {
        this.agentPpCommandService = agentPpCommandService;
    }
    @PostMapping("/save")
    public ResponseEntity<AgentPersonnePhysique> saveAgent(@RequestBody AgentPersonnePhysiqueDTO agentDTO) {

        AgentPersonnePhysique agentPersonnePhysique = convertDTOToEntity(agentDTO);

        AgentPersonnePhysique savedAgent = agentPpCommandService.save(agentPersonnePhysique);

        if (savedAgent != null) {
            return ResponseEntity.ok(savedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private AgentPersonnePhysique convertDTOToEntity(AgentPersonnePhysiqueDTO agentDTO) {
        AgentPersonnePhysique agent = new AgentPersonnePhysique();


        agent.setNom(agentDTO.getStep1().getLastname());
        agent.setPrenom(agentDTO.getStep1().getFirstname());
        agent.setDateDeNaissance(agentDTO.getStep1().getDdn());
        agent.setAdressePerso(agentDTO.getStep1().getAdressePersonnelle());
        agent.setAddresseCourier(agentDTO.getStep1().getAdresseCourrier());
        agent.setEstPPE(agentDTO.getStep1().isPPE());
        agent.setFonctionPpe(agentDTO.getStep1().getFonction());
        Document document = new Document();
        document.setIdDoc(agentDTO.getStep1().getDocid());
        document.setTypeDocument(TypeDocument.valueOf(agentDTO.getStep1().getDocumentType()));
        document.setDateEmission(agentDTO.getStep1().getDateEmission());
        document.setLieuEmission(agentDTO.getStep1().getLieuEmission());

        agent.setDocument(document);


        Compte compte = new Compte();

        compte.setRaison(agentDTO.getStep1().getRaison());

        compte.setAutreSourceAlimentationValue(agentDTO.getStep2().getAutreMoyenPaiementText());

        if (agentDTO.getStep2().isVirementespeces()) {
            compte.setSourceAlimentation(SourceAlimentation.VERSEMENT_ESPECE);
        }
        if (agentDTO.getStep2().isRevenusPro()) {
            compte.setSourceAlimentation(SourceAlimentation.REVENUS_PROFESSIONNELS);
        }
        if (agentDTO.getStep2().isProduitInvestissement()) {
            compte.setSourceAlimentation(SourceAlimentation.PRODUIT_INVESTISSEMENTS);
        }
        if (agentDTO.getStep2().isAutresource()) {
            compte.setSourceAlimentation(SourceAlimentation.AUTRE);
            compte.setAutreSourceAlimentationValue(agentDTO.getStep2().getAutreSourceText());
        } else {
            compte.setAutreSourceAlimentationValue(null);
        }
        compte.setTypeDeCompte(mapComptePaiement(agentDTO.getStep2().getComptePaiement()));

        // Create operationDebit
        Operation operationDebit = new Operation();
        operationDebit.setMontantMoyenHebdo(agentDTO.getStep2().getMontantsHebdomadairesdebit());
        operationDebit.setMontantMoyenMensuels(agentDTO.getStep2().getMontantsMensuelsdebit());
        operationDebit.setMontantMoyenJournaliers(Double.valueOf(agentDTO.getStep2().getMontantsJournaliersdebit()));

        operationDebit.setCash(agentDTO.getStep2().isCash());

        operationDebit.setVirement(agentDTO.getStep2().isVirementdebit());



        if (agentDTO.getStep2().isAutreMoyendebit()) {
            operationDebit.setAutreMoyen(agentDTO.getStep2().getAutreMoyendebitText());
        }
        operationDebit.setTypeOperation(TypeOperation.DEBIT);

        // Create operationCredit
        Operation operationCredit = new Operation();
        operationCredit.setMontantMoyenHebdo(agentDTO.getStep2().getMontantsHebdomadaires());
        operationCredit.setMontantMoyenMensuels(agentDTO.getStep2().getMontantsMensuels());
        operationCredit.setMontantMoyenJournaliers(agentDTO.getStep2().getMontantsJournaliers());

        operationCredit.setVirementInterne(agentDTO.getStep2().isVirementInterne());

        operationCredit.setVirementBancairePostaux(agentDTO.getStep2().isVirementBancaire());


        if (agentDTO.getStep2().isAutreMoyenPaiement()) {
            operationCredit.setAutreMoyen(agentDTO.getStep2().getAutreMoyenPaiementText());
        }
        operationCredit.setTypeOperation(TypeOperation.CREDIT);

        compte.setOperationDebit(operationDebit);
        compte.setOperationCredit(operationCredit);
        agent.setCompte(compte);

        // Map properties from Step3


        if (agentDTO.getStep3() != null && agentDTO.getStep3().getBanks() != null) {
            List<Banque> banques = agentDTO.getStep3().getBanks().stream()
                    .map(dtoBank -> {
                        Banque banque = new Banque();
                        banque.setNomBanque(dtoBank.getBanque());
                        banque.setAdresse(dtoBank.getAdressebanque());
                        banque.setSwift((dtoBank.getCode()));
                        banque.setRib(dtoBank.getNumCompte());

                        return banque;
                    })
                    .collect(Collectors.toList());
            agent.setBanqueEnRelation(banques);
        }



        return agent;


    }
    public static EtatDeCompte mapComptePaiement(String comptePaiement){
        switch (comptePaiement) {
            case "Niveau 1":
                return EtatDeCompte.NIVEAU_1;
            case "Niveau 2":
                return EtatDeCompte.NIVEAU_2;
            case "Niveau 3":
                return EtatDeCompte.NIVEAU_3;
            default:
                // Handle the case where the value is not recognized
                throw new IllegalArgumentException("Invalid comptePaiement value: " + comptePaiement);
        }
    }




    @GetMapping("/all")
    public ResponseEntity<List<AgentPersonnePhysique>> getAllAgents() {
        List<AgentPersonnePhysique> agents = agentPpCommandService.findAll();
        return ResponseEntity.ok(agents);
    }



    @PutMapping("/update")
    public ResponseEntity<AgentPersonnePhysique> updateAgent(@RequestBody AgentPersonnePhysique agentPersonnePhysique) {
        AgentPersonnePhysique updatedAgent = agentPpCommandService.update(agentPersonnePhysique);
        if (updatedAgent != null) {
            return ResponseEntity.ok(updatedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AgentPersonnePhysique> deleteAgent(@PathVariable("id") Long id) {
        AgentPersonnePhysique deletedAgent = agentPpCommandService.delete(id);
        if (deletedAgent != null) {
            return ResponseEntity.ok(deletedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AgentPersonnePhysique> saveAgent(@RequestBody AgentPersonnePhysique agentPersonnePhysique) {


        AgentPersonnePhysique savedAgent = agentPpCommandService.save(agentPersonnePhysique);
        if (savedAgent != null) {
            return ResponseEntity.ok(savedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
