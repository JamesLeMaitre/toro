package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.entities.Abonnement;

import java.util.List;

public interface CategorieREDSInterface {
    List<CategorieREDS> getAll();

    CategorieREDS create(CategorieREDS data);
    CategorieREDS edit(Long id,CategorieREDS data);
    CategorieREDS getByIds(Long id);

    void delete(Long id);

    int count();
}
