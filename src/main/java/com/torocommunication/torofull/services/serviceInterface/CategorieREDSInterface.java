package com.torocommunication.torofull.services.serviceInterface;

import com.torocommunication.torofull.entities.CategorieREDS;

import java.util.List;

public interface CategorieREDSInterface {
    List<CategorieREDS> getAll();
    CategorieREDS getById(Long id);
    CategorieREDS update(CategorieREDS data, Long id);
    void delete(Long id);
    CategorieREDS save(CategorieREDS data);
}
