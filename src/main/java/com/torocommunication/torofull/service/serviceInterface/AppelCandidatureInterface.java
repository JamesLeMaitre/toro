package com.torocommunication.torofull.service.serviceInterface;


import com.torocommunication.torofull.entities.AppelCandidature;

import java.util.List;

public interface AppelCandidatureInterface {


    List<AppelCandidature> getAll();

    AppelCandidature create(AppelCandidature data);
    AppelCandidature edit(Long id,AppelCandidature data);
    AppelCandidature getById(Long id);

    void disable(Long id);

    void enable(Long id);

    int count();
    List<AppelCandidature> findByIdUea(Long id);

   List<AppelCandidature>  listAppelCandidatureByTypeOffre() ;
}
