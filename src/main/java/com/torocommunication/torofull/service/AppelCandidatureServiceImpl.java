package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.AppelCandidature;
import com.torocommunication.torofull.repo.AppelCandidatureRepo;
import com.torocommunication.torofull.service.serviceInterface.AppelCandidatureInterface;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class AppelCandidatureServiceImpl implements AppelCandidatureInterface, Serializable {

    private final AppelCandidatureRepo appelCandidatureRepo;


    public AppelCandidatureServiceImpl(AppelCandidatureRepo appelCandidatureRepo) {
        this.appelCandidatureRepo = appelCandidatureRepo;
       ;
    }


    @Override
    public List<AppelCandidature> getAll() {
        return appelCandidatureRepo.findAll();
    }

    @Override
    public AppelCandidature create(AppelCandidature data) {


        return appelCandidatureRepo.save(data);
    }

    @Override
    public AppelCandidature edit(Long id, AppelCandidature data) {

        AppelCandidature appelCandidature=getById(id);

        appelCandidature.setAdresse(data.getAdresse());
        appelCandidature.setDescription(data.getDescription());
        appelCandidature.setDetailSA(data.getDetailSA());
        appelCandidature.setLibelle(data.getLibelle());
        appelCandidature.setUea(data.getUea());
        appelCandidature.setLibelle(data.getLibelle());
        appelCandidature.setResume(data.getResume());



        return appelCandidatureRepo.save(appelCandidature);
    }

    @Override
    public AppelCandidature getById(Long id) {
        return appelCandidatureRepo.findById(id).orElse(null);
    }

    @Override
    public void disable(Long id) {

        AppelCandidature appelCandidature=getById(id);


        appelCandidature.setEtat(false);
    }

    @Override
    public void enable(Long id) {

        AppelCandidature appelCandidature=getById(id);


        appelCandidature.setEtat(true);
    }


    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<AppelCandidature> findByIdUea(Long id) {
        return appelCandidatureRepo.listAppelCandidatureByIdUea(id);
    }

    @Override
    public List<AppelCandidature> listAppelCandidatureByTypeOffre() {
        return appelCandidatureRepo.listAppelCandidatureByTypeOffre();
    }
}
