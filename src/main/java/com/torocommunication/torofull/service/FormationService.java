package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.Formation;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.repo.FormationRepository;
import com.torocommunication.torofull.service.serviceInterface.FormationInterface;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FormationService implements FormationInterface {
    private final FormationRepository formationRepository;
    private final UtilisateurUEAInterface utilisateurUEAInterface;

    public FormationService(FormationRepository formationRepository, UtilisateurUEAInterface utilisateurUEAInterface) {
        this.formationRepository = formationRepository;

        this.utilisateurUEAInterface = utilisateurUEAInterface;
    }

    @Override
    public List<Formation> getAll() {
        return formationRepository.findAll();
    }

    @Override
    public Formation create(Formation data) {
        UtilisateurUEA utilisateurUEA = utilisateurUEAInterface.getByID(data.getUtilisateurUEA().getId());
        if(utilisateurUEA !=null){
            return formationRepository.save(data);
        }
        return  null;
    }

    @Override
    public Formation edit(Long id, Formation data) {
        Formation formation =new Formation();
        data = getByIds(id);
        formation.setId(data.getId());
        formation.setLibelle(data.getLibelle());
        formation.setUtilisateurUEA(data.getUtilisateurUEA());
        return null;
    }

    @Override
    public Formation getByIds(Long id) {
        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public int count() {
        return formationRepository.countBy();
    }
}
