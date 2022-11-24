package com.torocommunication.torofull.services.serviceImpl;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.repositories.AbonnementRepository;
import com.torocommunication.torofull.services.serviceInterface.AbonnementInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AbonnementServiceImpl implements AbonnementInterface {
    private final AbonnementRepository abonnementRepository;
    @Override
    public List<Abonnement> getAllAbonnement() {
        return abonnementRepository.findAll();
    }

    @Override
    public Abonnement getAbonnement(Long id) {
        return abonnementRepository.findById(id).orElse(null);
    }

    @Override
    public Abonnement saveAbonnement(Abonnement data, Long id) {
        Abonnement abonnement = abonnementRepository.findById(id).orElse(null);
        abonnement.setEtat(data.getEtat());
        abonnement.setId(data.getId());
        abonnement.setLibelle(data.getLibelle());
        return abonnementRepository.save(abonnement);
    }

    @Override
    public void deleteAbonnement(Long id) {
        abonnementRepository.deleteById(id);
    }
}
