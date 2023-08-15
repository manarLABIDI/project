package com.hydatis.KycmicroserviceCQRS.command.model;

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
@Table(name = "responsables")
public class Responsable extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    @Column(nullable = false, unique = true )
    private String signature;

    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DemandeEngagement> demandeEngagements;


}

