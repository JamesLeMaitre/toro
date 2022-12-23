package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.SecteurDactivite;
import com.torocommunication.torofull.repo.SecteurDactiviteRepository;
import com.torocommunication.torofull.service.serviceInterface.SecteurDactiviteInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SecteurDactiviteService implements SecteurDactiviteInterface {
private final SecteurDactiviteRepository secteurDactiviteRepository;

    public SecteurDactiviteService(SecteurDactiviteRepository secteurDactiviteRepository) {
        this.secteurDactiviteRepository = secteurDactiviteRepository;
    }

    @Override
    public List<SecteurDactivite> getAll() {
        return secteurDactiviteRepository.findAll();
    }

    @Override
    public SecteurDactivite create(SecteurDactivite data) {
        return secteurDactiviteRepository.save(data);
    }

    @Override
    public SecteurDactivite edit(Long id, SecteurDactivite data) {
        SecteurDactivite secteurDactivite = new SecteurDactivite();
        data = getByIds(id);
        secteurDactivite.setId(data.getId());
        secteurDactivite.setLibelle(data.getLibelle());
        return secteurDactiviteRepository.save(secteurDactivite);
    }

    @Override
    public SecteurDactivite getByIds(Long id) {
        return secteurDactiviteRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        secteurDactiviteRepository.deleteById(id);
    }

    @Override
    public int count() {
        return secteurDactiviteRepository.countBy();
    }
}
