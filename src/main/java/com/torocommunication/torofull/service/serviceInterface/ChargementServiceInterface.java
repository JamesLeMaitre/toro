package com.torocommunication.torofull.service.serviceInterface;


import com.torocommunication.torofull.entities.Chargement;

public interface ChargementServiceInterface {

    Chargement getByIdUeaAndAppel(Long idUea,Long appel);


    Chargement chargerOffre (Long idUea, Long appel);
}
