package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "agentPersonnePhysique")
public class AgentPersonnePhysique extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String adressePerso;
    private String addresseCourier;
    private Boolean estPPE;
    private String fonctionPpe;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_id")
    private Compte compte;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "categorie_socio_pro_id")
    private CategorieSocioProfesionnelle categorieSocioProfesionnelle;
    private Boolean estBeneficiareEffectifs;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private AgentPersonnePhysique beneficiaireEffectifs;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy ="beneficiaireEffectifs",optional = true)
    private AgentPersonnePhysique titulaireDuCompte;

    @ManyToMany
    @JoinTable(
            name = "PP_BANQUE_EN_RELATION",
            joinColumns = @JoinColumn(name = "personne_physique_id"),
            inverseJoinColumns = @JoinColumn(name = "banque_id")
    )
    private List<Banque> banqueEnRelation;



}
