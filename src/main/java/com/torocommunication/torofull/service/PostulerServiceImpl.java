package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.Postuler;
import com.torocommunication.torofull.repo.AppelCandidatureRepo;
import com.torocommunication.torofull.repo.PostulerRepo;
import com.torocommunication.torofull.repo.UtilisateurUEARepo;
import com.torocommunication.torofull.service.serviceInterface.ChargementServiceInterface;
import com.torocommunication.torofull.service.serviceInterface.PostulerServiceInterface;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostulerServiceImpl implements PostulerServiceInterface {



    @Autowired
    private PostulerRepo postulerRepo;



    @Autowired
    private AppelCandidatureRepo candidatureRepo;


    @Autowired
    private  ChargementServiceInterface anInterface;

    @Autowired
    private UtilisateurUEAInterface ueaInterface;





    @Override
    public Postuler createPostuler(String cv ,String lm, Long appel,Long uea,Long chargement) {

        Postuler postuler=new Postuler();

        postuler.setCurriculumVitae(cv);
        postuler.setLettreMotivation(lm);
        postuler.setAppelCandidature(candidatureRepo.findById(appel).get());
        postuler.setUea(ueaInterface.getById(uea));


        anInterface.dissable(chargement);



        return postulerRepo.save(postuler);
    }
}
