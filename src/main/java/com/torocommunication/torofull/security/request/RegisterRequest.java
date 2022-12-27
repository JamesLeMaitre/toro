package com.torocommunication.torofull.security.request;

import com.sun.istack.NotNull;
import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.TypeUEA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {



    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String roleName;
    @NotNull
    private String adresse;
    @NotNull
    private String dateNaissance;

    @NotNull
    private String genre;

    @NotNull
    private String tel;

    @NotNull
    private Long typeUEA;

    @NotNull
    private Long detailSA;

}
