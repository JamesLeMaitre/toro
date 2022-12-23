package com.torocommunication.torofull.service.serviceInterface;



import com.torocommunication.torofull.entities.Abonnement;

import java.util.List;

public interface AbonnementInterface {
    List<Abonnement> getAll();

    Abonnement create(Abonnement data);
    Abonnement edit(Long id,Abonnement data);
    Abonnement getByIds(Long id);

    void delete(Long id);

    int count();
}
