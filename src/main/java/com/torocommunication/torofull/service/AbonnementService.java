package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.repo.AbonnementRepository;
import com.torocommunication.torofull.service.serviceInterface.AbonnementInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbonnementService implements AbonnementInterface {

    private final AbonnementRepository abonnementRepository;


    public AbonnementService(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;

    }

    @Override
    public List<Abonnement> getAll() {
        return abonnementRepository.findAll();
    }

    @Override
    public Abonnement create(Abonnement data) {
        return abonnementRepository.save(data);
    }

    @Override
    public Abonnement edit(Long id, Abonnement data) {
        Abonnement abonnement = new Abonnement();
        data = abonnementRepository.findById(id).orElse(null);
        abonnement.setId(data.getId());
        abonnement.setLibelle(data.getLibelle());
        abonnement.setEtat(data.getEtat());
        return abonnementRepository.save(abonnement);
    }

    @Override
    public Abonnement getByIds(Long id) {
        return abonnementRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        abonnementRepository.deleteById(id);
    }

    @Override
    public int count() {
        return abonnementRepository.countBy();
    }
}
