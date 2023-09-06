package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Personne {

    @Column
    private String nationalite;
    @Column
    private String residence;
    @Column
    private boolean estResident;
    @Column
    private Long telephone;
    @Column
    private String email;

    @OneToOne(mappedBy = "agent", cascade = CascadeType.ALL)
    private DemandeEngagement demandeEngagement;
}