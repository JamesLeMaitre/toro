package com.torocommunication.torofull.services.serviceInterface;

import com.torocommunication.torofull.entities.Abonnement;

import java.util.List;

public interface AbonnementInterface {

    List<Abonnement> getAllAbonnement();
    Abonnement getAbonnement(Long id);
    Abonnement create(Abonnement data);
    Abonnement update(Abonnement data,Long id);
    void deleteAbonnement(Long id);
}
