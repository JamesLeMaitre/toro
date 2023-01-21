package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.HistoriqueUEA;
import com.torocommunication.torofull.repo.HistoriqueUEARepo;
import com.torocommunication.torofull.service.serviceInterface.HistoriqueUEAInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistoriqueUEAServiceImpl implements HistoriqueUEAInterface {


    private final HistoriqueUEARepo historiqueUEARepo;

    public HistoriqueUEAServiceImpl(HistoriqueUEARepo historiqueUEARepo) {
        this.historiqueUEARepo = historiqueUEARepo;
    }


    @Override
    public List<HistoriqueUEA> getAll() {
        return historiqueUEARepo.findAll();
    }

    @Override
    public HistoriqueUEA create(HistoriqueUEA data) {
        return historiqueUEARepo.save(data);
    }

    @Override
    public HistoriqueUEA getById(Long id) {
        return historiqueUEARepo.findById(id).orElse(null);
    }

    @Override
    public List<HistoriqueUEA> getByIdUEA(Long id) {
        return historiqueUEARepo.listHistoriqueUEAByIdUEA(id);
    }
}
