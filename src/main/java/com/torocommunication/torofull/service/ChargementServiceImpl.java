package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.AppelCandidature;
import com.torocommunication.torofull.entities.Chargement;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.repo.ChargementRepo;
import com.torocommunication.torofull.service.serviceInterface.AppelCandidatureInterface;
import com.torocommunication.torofull.service.serviceInterface.ChargementServiceInterface;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChargementServiceImpl implements ChargementServiceInterface {


    @Autowired
    private ChargementRepo chargementRepo;


    @Autowired
    private UtilisateurUEAInterface ueaInterface;

    @Autowired
    private AppelCandidatureInterface candidatureInterface;


    @Override
    public Chargement getByIdUeaAndAppel(Long idUea, Long appel) {
        return chargementRepo.findByIdUeaAndCandidature(idUea,appel);
    }

    @Override
    public Chargement chargerOffre(Long idUea, Long appel) {


        AppelCandidature candidature=candidatureInterface.getById(appel);
        UtilisateurUEA uea=ueaInterface.getById(idUea);



        Chargement chargement=new Chargement();

        chargement.setEtat(true);
        chargement.setCandidature(candidature);
        chargement.setUea(uea);
        chargement.setLibellePoste(candidature.getLibelle());


        Chargement  chargement1=chargementRepo.save(chargement);
        return chargement1;
    }

    @Override
    public List<Chargement> findByIdUea(Long idUea) {
        return chargementRepo.findByIdUea(idUea);
    }


    @Override
    public Chargement getById(Long id) {

        return chargementRepo.findById(id).orElse(null);
    }


    @Override
    public void  dissable(Long id) {

        Chargement chargement=getById(id);
        chargement.setEtat(false);

        chargementRepo.save(chargement);
    }
}
