package com.torocommunication.torofull.services.serviceInterface;

import com.torocommunication.torofull.entities.Abonnement;

import java.util.List;

public interface AbonnementInterfaces {

    List<Abonnement> getAllAbonnement();
    Abonnement getAbonnement(Long id);
    Abonnement saveAbonnement(Abonnement data,Long id);
    void deleeteAbonnement(Long id);
}
