package com.torocommunication.torofull.service.serviceInterface;


import com.torocommunication.torofull.entities.Chargement;

import java.util.List;

public interface ChargementServiceInterface {

    Chargement getByIdUeaAndAppel(Long idUea,Long appel);


    Chargement chargerOffre (Long idUea, Long appel);


    List<Chargement> findByIdUea (Long idUea);

    Chargement getById(Long id);

    void  dissable(Long id);
}
