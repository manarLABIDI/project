package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}