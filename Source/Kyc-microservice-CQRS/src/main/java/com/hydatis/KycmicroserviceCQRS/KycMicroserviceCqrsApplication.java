package com.hydatis.KycmicroserviceCQRS;

import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.model.CategorieSocioProfesionnelle;
import com.hydatis.KycmicroserviceCQRS.command.model.Compte;
import com.hydatis.KycmicroserviceCQRS.command.model.Document;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.SourceAlimentation;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDocument;
import com.hydatis.KycmicroserviceCQRS.command.repository.AgentPPRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.CategorieSocioProfesionnelleRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.CompteRepository;
import com.hydatis.KycmicroserviceCQRS.command.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

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
					.document(document)  // Set the initialized Document
					.categorieSocioProfesionnelle(categorieSocioProfessionnelle)
					.estBeneficiareEffectifs(true)

					.build();

			agent.setBeneficiaireEffectifs(agent);
			agent.setTitulaireDuCompte(agent);

			agentPPRepository.save(agent);

			compte.setTitulaire(agent);
			compteRepository.save(compte);

			document.setTitulaireDuDocument(agent);
			documentRepository.save(document);

			categorieSocioProfessionnelle.setAgentPersonnePhysique(agent);
			categorieSocioProfesionnelleRepository.save(categorieSocioProfessionnelle);
		};
	}

}
