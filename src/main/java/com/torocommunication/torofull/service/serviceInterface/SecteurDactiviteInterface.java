package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.SecteurDactivite;

import java.util.List;

public interface SecteurDactiviteInterface {
    List<SecteurDactivite> getAll();

    SecteurDactivite create(SecteurDactivite data);
    SecteurDactivite edit(Long id,SecteurDactivite data);
    SecteurDactivite getByIds(Long id);

    void delete(Long id);

    int count();
}
