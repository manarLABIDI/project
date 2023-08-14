package com.hydatis.KycmicroserviceCQRS.command.model;

import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class CategorieSocioProfesionnelle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TypeAgent typeAgent;
    private String raisonSociale;
    private String adresseProfessionelle;
    private String telephone;
    private String email;
    private String typeActivite;
    private String zoneGeo;
    @OneToOne(mappedBy = "categorieSocioProfesionnelle")
    private AgentPersonnePhysique agentPersonnePhysique;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_id")
    private Compte compte;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categorie_socio_pro_id")
    private CategorieSocioProfesionnelle categorieSocioProfesionnelle;
    private Boolean estBeneficiareEffectifs;

    @OneToOne(optional = true)
    private AgentPersonnePhysique beneficiaireEffectifs;

    @OneToOne(mappedBy ="beneficiaireEffectifs",optional = true)
    private AgentPersonnePhysique titulaireDuCompte;

    @ManyToMany
    @JoinTable(
            name = "PP_BANQUE_EN_RELATION",
            joinColumns = @JoinColumn(name = "personne_physique_id"),
            inverseJoinColumns = @JoinColumn(name = "banque_id")
    )
    private List<Banque> banqueEnRelation;
}
