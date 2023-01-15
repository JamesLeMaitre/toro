package com.torocommunication.torofull.security.response;


import com.torocommunication.torofull.entities.UtilisateurUEA;
import lombok.Data;

@Data
public class UEAreponse {

    private String token;
    private  Object  uea;
}
