package com.torocommunication.torofull.security.response;

import com.torocommunication.torofull.entities.RoleUEA;

import java.util.Collection;
import java.util.Date;

public class AppUserReponseDem {


    private String username;

    private String email;

    private String password;


    private String adresse;

    private Date dateNaissance;


    private String genre;
    private String tel;

    private Boolean isNotLocked;

    private Boolean isActive;

    private Collection<RoleUEA> roles;
}
