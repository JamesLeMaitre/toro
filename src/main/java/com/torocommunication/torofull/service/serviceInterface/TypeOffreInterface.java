package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.TypeOffre;
import com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.entities.Abonnement;

import java.util.List;

public interface TypeOffreInterface {
    List<TypeOffre> getAll();

    TypeOffre create(TypeOffre data);
    TypeOffre edit(Long id,TypeOffre data);
    TypeOffre getByIds(Long id);

    void delete(Long id);

    int count();
}
