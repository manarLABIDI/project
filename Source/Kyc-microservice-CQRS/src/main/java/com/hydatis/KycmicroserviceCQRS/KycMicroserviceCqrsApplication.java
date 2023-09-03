package com.hydatis.KycmicroserviceCQRS;

import com.hydatis.KycmicroserviceCQRS.command.model.*;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.SourceAlimentation;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDocument;
import com.hydatis.KycmicroserviceCQRS.command.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.hydatis.KycmicroserviceCQRS.*")
public class KycMicroserviceCqrsApplication {

	@Autowired
	private AgentPPRepository agentPPRepository;

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CategorieSocioProfesionnelleRepository categorieSocioProfesionnelleRepository;
	@Autowired
	private BanqueRepository banqueRepository;


	public static void main(String[] args) {
		SpringApplication.run(KycMicroserviceCqrsApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer() {
		return args -> {
			// Initialize data here
			Document document = Document.builder()
					.dateEmission(LocalDateTime.of(2022, 1, 1, 0, 0))
					.dateExpiration(LocalDateTime.of(2032, 1, 1, 0, 0))
					.lieuEmission("City of Emission")
					.typeDocument(TypeDocument.CIN)
					// Set other Document properties
					.build();

			Compte compte = Compte.builder()
					.raison("Business Account")
					.sourceAlimentation(SourceAlimentation.REVENUS_PROFESSIONNELS)
					.typeDeCompte(EtatDeCompte.NIVEAU_1)
					// Set other Compte properties
					.build();

			CategorieSocioProfesionnelle categorieSocioProfessionnelle = CategorieSocioProfesionnelle.builder()
					.typeAgent(TypeAgent.RETRAITE)
					.raisonSociale("My Company")
					.adresseProfessionelle("123 Business Street")
					.telephone("123-456-7890")
					.email("company@example.com")
					.typeActivite("Some Activity")
					.zoneGeo("Some Zone")
					.build();

			AgentPersonnePhysique agent = AgentPersonnePhysique.builder()
					.nom("John")
					.prenom("Doe")
					.dateDeNaissance(LocalDateTime.of(1990, 1, 1, 0, 0))
					.adressePerso("123 Main Street")
					.addresseCourier("456 Courier Avenue")
					.estPPE(true)
					.fonctionPpe("Manager")
					.compte(compte)
					.document(document)
					.categorieSocioProfesionnelle(categorieSocioProfessionnelle)
					.estBeneficiareEffectifs(true)
					.build();

			// Save AgentPersonnePhysique first
			agent.setBeneficiaireEffectifs(agent);
			agent.setTitulaireDuCompte(agent);
			agentPPRepository.save(agent);

			// Now, save Banque entities
			Banque banque1 = Banque.builder()
					.adresse("Bank Address")
					.swift("SWIFT4")
					.rib("RIB4")
					.build();

			Banque banque2 = Banque.builder()
					.adresse("Bank Address 3")
					.swift("SWIFT3")
					.rib("RIB3")
					.build();

			banqueRepository.saveAll(Arrays.asList(banque1, banque2));

			// Update Banque-AgentPersonnePhysique associations
			agent.setBanqueEnRelation(Arrays.asList(banque1, banque2));
			agentPPRepository.save(agent);

			// Update Compte-AgentPersonnePhysique associations
			compte.setTitulaire(agent);
			compteRepository.save(compte);

			// Update Document-AgentPersonnePhysique associations
			document.setTitulaireDuDocument(agent);
			documentRepository.save(document);

			// Update CategorieSocioProfesionnelle-AgentPersonnePhysique associations
			categorieSocioProfessionnelle.setAgentPersonnePhysique(agent);
			categorieSocioProfesionnelleRepository.save(categorieSocioProfessionnelle);
		};
	}


}