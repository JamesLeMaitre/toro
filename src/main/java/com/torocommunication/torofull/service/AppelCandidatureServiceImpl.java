package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.AppelCandidature;
import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.JobType;
import com.torocommunication.torofull.repo.AppelCandidatureRepo;
import com.torocommunication.torofull.repo.DetailSARepository;
import com.torocommunication.torofull.repo.TypeJobRepo;
import com.torocommunication.torofull.service.serviceInterface.AppelCandidatureInterface;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AppelCandidatureServiceImpl implements AppelCandidatureInterface, Serializable {

    private final AppelCandidatureRepo appelCandidatureRepo;

    private final DetailSARepository detailSARepository;

    private final TypeJobRepo typeJobRepo;

    public AppelCandidatureServiceImpl(AppelCandidatureRepo appelCandidatureRepo, DetailSARepository detailSARepository, TypeJobRepo typeJobRepo) {
        this.appelCandidatureRepo = appelCandidatureRepo;
        this.detailSARepository = detailSARepository;
        this.typeJobRepo = typeJobRepo;
        ;
    }


    @Override
    public List<AppelCandidature> getAll() {
        return appelCandidatureRepo.findAll();
    }

    @Override
    public AppelCandidature create(AppelCandidature data) {


        DetailSA detailSA=detailSARepository.findById(data.getDetailSA().getId()).get();


        data.setDetailSA(detailSA);
        AppelCandidature   appelCandidature  =  appelCandidatureRepo.save(data);


        Optional<AppelCandidature> app1= appelCandidatureRepo.findById(appelCandidature.getId());


        for (JobType t: data.getJobTypes()) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("dataaaa"+data.getJobTypes());
            Optional<JobType> job = typeJobRepo.findById(t.getId());
            System.out.println("job id" +t.getId());
            System.out.println("app id" +appelCandidature.getId() );







            Set<Optional<JobType>> roleSet = new HashSet<>();
            roleSet.add(job);


        }



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
