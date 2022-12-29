package com.torocommunication.torofull.security.response;


import com.sun.istack.NotNull;
import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.TypeUEA;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class AppUserResponseStagiaire {




    private String nom;


    private String prenom;
    private String username;

    private String email;
    private String adresse;

    private String dateNaissance;


    private String genre;

    private String tel;

    private Boolean isNotLocked;

    private Boolean isActive;

    private Collection<RoleUEA> roles;


    private TypeUEA typeUEA;

    private DetailSA detailSA;











}
