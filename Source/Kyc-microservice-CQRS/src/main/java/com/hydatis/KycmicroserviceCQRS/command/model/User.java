package com.hydatis.KycmicroserviceCQRS.command.model;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User  {

    private String username;
    private String password;
    private String email;
    private String role;


}


